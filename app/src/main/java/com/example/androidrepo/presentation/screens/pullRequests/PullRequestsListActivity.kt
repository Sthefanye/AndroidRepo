package com.example.androidrepo.presentation.screens.pullRequests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrepo.databinding.ActivityPullRequestsListBinding
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.presentation.screens.pullRequests.adapter.PullRequestsListAdapter
import com.example.androidrepo.presentation.screens.pullRequests.viewmodel.PullRequestListViewModel
import com.example.androidrepo.utils.Constants
import com.example.androidrepo.utils.common.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PullRequestsListActivity : AppCompatActivity() {
    private var binding: ActivityPullRequestsListBinding? = null
    private val viewModel: PullRequestListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestsListBinding.inflate(layoutInflater)

        intent.extras?.let {
            val repository = it.getString(Constants.Extras.REPOSITORY_EXTRA) ?: ""
            val name = it.getString(Constants.Extras.USERNAME_EXTRA) ?: ""
            viewModel.getPullRequests(repository, name)
        }

        bindObservers()
        setContentView(binding?.root)
    }
    private fun loadListInRecycler(listRepo: List<PullRequests>) {
        val adapter = PullRequestsListAdapter(listRepo) {
            openPullRequestInBrowser(it)
        }
        binding?.rcListPullRequests?.layoutManager = LinearLayoutManager(this)
        binding?.rcListPullRequests?.adapter = adapter
    }

    private fun bindObservers() {
        lifecycleScope.launch {
            viewModel.pullRequestsList.observe(this@PullRequestsListActivity) {
                when(it) {
                    is NetworkResult.Loading -> println("Loading")
                    is NetworkResult.Success -> {
                        loadListInRecycler(it.data)
                    }
                    is NetworkResult.Failure -> println("Failure")
                }
            }
        }
    }

    private fun openPullRequestInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}