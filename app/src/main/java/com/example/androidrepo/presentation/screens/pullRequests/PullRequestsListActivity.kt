package com.example.androidrepo.presentation.screens.pullRequests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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
    private var repository =  ""
    private var name =  ""
    private var avatar =  ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestsListBinding.inflate(layoutInflater)

        repository =  intent.extras?.getString(Constants.Extras.REPOSITORY_EXTRA) ?: ""
        name =  intent.extras?.getString(Constants.Extras.USERNAME_EXTRA) ?: ""
        avatar =  intent.extras?.getString(Constants.Extras.AVATAR_EXTRA) ?: ""

        viewModel.getPullRequests(repository, name)
        bindingInfosInView("$repository/$name", avatar)
        bindObservers()

        setContentView(binding?.root)
    }

    private fun bindingInfosInView(name: String, avatar: String) {
        binding?.tvUsername?.text = name
        binding?.ivOwnerAvatar?.let { Glide.with(this).load(avatar).into(it) }
    }

    private fun bindObservers() {
        lifecycleScope.launch {
            viewModel.pullRequestsList.observe(this@PullRequestsListActivity) {
                when(it) {
                    is NetworkResult.Loading -> loadProgress(true)
                    is NetworkResult.Success -> {
                        if (it.data.isEmpty()) {
                            loadEmptyExceptionScreen()
                            return@observe
                        }
                        loadListInRecycler(it.data)
                    }
                    is NetworkResult.Failure ->{
                        Log.e(Constants.TAG, it.message)
                        loadAlertException()
                    }
                }
            }
        }
    }

    private fun loadListInRecycler(listRepo: List<PullRequests>) {
        loadProgress(false)
        val adapter = PullRequestsListAdapter(listRepo) {
            openPullRequestInBrowser(it)
        }
        binding?.rcListPullRequests?.layoutManager = LinearLayoutManager(this)
        binding?.rcListPullRequests?.adapter = adapter
    }

    private fun openPullRequestInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        startActivity(intent)
    }

    private fun loadEmptyExceptionScreen() {
        loadProgress(false)
        binding?.rcListPullRequests?.visibility = View.GONE
        binding?.ivEmptyList?.visibility = View.VISIBLE
        binding?.tvEmptyList?.visibility = View.VISIBLE
    }

    private fun loadAlertException() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("The information could not be uploaded, please try again or come back later ")
            .setTitle("Failed to load repositories")
            .setPositiveButton("Try again") { _, _ ->
                loadProgress(true)
                viewModel.getPullRequests(repository, name)
            }
            .setNegativeButton("Cancel") { _, _ ->
                finish()
                finishAffinity()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun loadProgress(isLoading: Boolean) {
        binding?.cpProgressIndicator?.progress
        binding?.cpProgressIndicator?.visibility = if (isLoading) View.VISIBLE else  View.GONE
        binding?.cpProgressIndicator?.setProgress(100, isLoading)
    }


    override fun onResume() {
        super.onResume()
        viewModel.getPullRequests(repository, name)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}