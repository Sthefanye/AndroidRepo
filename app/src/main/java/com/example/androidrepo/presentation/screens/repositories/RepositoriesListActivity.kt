package com.example.androidrepo.presentation.screens.repositories

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.androidrepo.databinding.ActivityRepositoriesListBinding
import com.example.androidrepo.domain.model.Items
import com.example.androidrepo.presentation.screens.pullRequests.PullRequestsListActivity
import com.example.androidrepo.presentation.screens.repositories.adapter.RepositoriesListAdapter
import com.example.androidrepo.presentation.screens.repositories.viewmodel.RepositoriesListViewModel
import com.example.androidrepo.utils.Constants
import com.example.androidrepo.utils.common.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoriesListActivity : AppCompatActivity() {
    private var binding: ActivityRepositoriesListBinding? = null
    private val viewModel: RepositoriesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoriesListBinding.inflate(layoutInflater)

        viewModel.getRepositories()
        bindObservers()
        listenersObservers()
        setContentView(binding?.root)
    }

    private fun loadAdapter(listRepo: List<Items>) {
        val adapter = RepositoriesListAdapter(listRepo) { repository, name ->
            val intent = Intent(this, PullRequestsListActivity::class.java)
            intent.apply {
                action = Intent.ACTION_SEND
                putExtra(Constants.Extras.USERNAME_EXTRA, name)
                putExtra(Constants.Extras.REPOSITORY_EXTRA, repository)
            }
            startActivity(intent)
        }
        binding?.rcListRepositories?.layoutManager = LinearLayoutManager(this)
        binding?.rcListRepositories?.adapter = adapter

    }

    private fun bindObservers() {
        lifecycleScope.launch {
            viewModel.itemList.observe(this@RepositoriesListActivity) {
                when (it) {
                    is NetworkResult.Loading -> println("Loading")
                    is NetworkResult.Success -> {
                        loadAdapter(it.data)
                    }

                    is NetworkResult.Failure -> println("Failure")
                }
            }
        }
    }

    private fun listenersObservers() {
        binding?.srUpdate?.setOnRefreshListener {
            viewModel.getRepositories()
            binding?.srUpdate?.isRefreshing = false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}