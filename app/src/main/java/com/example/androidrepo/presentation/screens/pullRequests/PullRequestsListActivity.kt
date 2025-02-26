package com.example.androidrepo.presentation.screens.pullRequests

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrepo.R
import com.example.androidrepo.databinding.ActivityPullRequestsListBinding
import com.example.androidrepo.domain.model.pulls.PullRequests
import com.example.androidrepo.presentation.screens.pullRequests.adapter.PullRequestsListAdapter
import com.example.androidrepo.presentation.screens.pullRequests.viewmodel.PullRequestListViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class PullRequestsListActivity : AppCompatActivity() {
    private var binding: ActivityPullRequestsListBinding? = null
    private val viewModel: PullRequestListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestsListBinding.inflate(layoutInflater)

        supportActionBar?.title = getString(R.string.repositories_title_toolbar)

        viewModel.getPullRequests(owner = "jetbrains", repo = "kotlin").enqueue(object : Callback<List<PullRequests>> {
            override fun onResponse(call: Call<List<PullRequests>>, response: Response<List<PullRequests>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        loadListInRecycler(it)
                    }

                } else {
                    println(
                        "Erro na resposta: ${response.code()} , ${
                            response.errorBody().toString()
                        }"
                    )
                }
            }

            override fun onFailure(call: Call<List<PullRequests>>, error: Throwable) {
                println("Falha na requisição: ${error.message}")
            }
        })

        setContentView(binding?.root)
    }

    fun loadListInRecycler(listRepo: List<PullRequests>) {
        val adapter = PullRequestsListAdapter(listRepo)
        binding?.rcListPullRequests?.layoutManager = LinearLayoutManager(this)
        binding?.rcListPullRequests?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}