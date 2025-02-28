package com.example.androidrepo.presentation.screens.repositories

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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


    private fun bindObservers() {
        lifecycleScope.launch {
            viewModel.itemList.observe(this@RepositoriesListActivity) {
                when (it) {
                    is NetworkResult.Loading -> loadProgress(true)
                    is NetworkResult.Success -> {
                        loadProgress(false)
                        loadAdapter(it.data)
                    }
                    is NetworkResult.Failure -> {
                        Log.e(Constants.TAG, it.message)
                        loadAlertException()
                    }
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

    private fun loadAdapter(listRepo: List<Items>) {
        val adapter = RepositoriesListAdapter(listRepo) { repository, name, avatar ->
            val intent = Intent(this, PullRequestsListActivity::class.java)
            intent.apply {
                action = Intent.ACTION_SEND
                putExtra(Constants.Extras.USERNAME_EXTRA, name)
                putExtra(Constants.Extras.REPOSITORY_EXTRA, repository)
                putExtra(Constants.Extras.AVATAR_EXTRA, avatar)
            }
            startActivity(intent)
        }
        binding?.rcListRepositories?.layoutManager = LinearLayoutManager(this)
        binding?.rcListRepositories?.adapter = adapter

    }

    private fun loadAlertException() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("The information could not be uploaded, please try again or come back later ")
            .setTitle("Failed to load repositories")
            .setPositiveButton("Try again") { _, _ ->
                viewModel.getRepositories()
                loadProgress(true)
            }
            .setNegativeButton("Cancel") { _, _ ->
               finish()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun loadProgress(isLoading: Boolean) {
        binding?.cpProgressIndicator?.progress
        binding?.cpProgressIndicator?.visibility = if (isLoading) View.VISIBLE else  View.GONE
        binding?.cpProgressIndicator?.setProgress(100, isLoading)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}