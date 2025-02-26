package com.example.androidrepo.presentation.screens.repositories

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrepo.R
import com.example.androidrepo.databinding.ActivityRepositoriesListBinding
import com.example.androidrepo.domain.model.repositories.Items
import com.example.androidrepo.domain.model.repositories.Repositories
import com.example.androidrepo.presentation.screens.pullRequests.PullRequestsListActivity
import com.example.androidrepo.presentation.screens.repositories.adapter.RepositoriesListAdapter
import com.example.androidrepo.presentation.screens.repositories.viewmodel.RepositoriesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class RepositoriesListActivity : AppCompatActivity() {
    private var binding: ActivityRepositoriesListBinding? = null
    private val viewModel: RepositoriesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoriesListBinding.inflate(layoutInflater)

        supportActionBar?.title = getString(R.string.repositories_title_toolbar)

        viewModel.getRepositories().enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        loadListInRecycler(it.items)
                    }

                } else {
                    println("Erro na resposta: ${response.code()} , ${
                        response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<Repositories>, error: Throwable) {
                println("Falha na requisição: ${error.message}")
            }
        })

        setContentView(binding?.root)
    }

    fun loadListInRecycler(listRepo: List<Items>) {
        val adapter = RepositoriesListAdapter(listRepo) {
            val intent = Intent(this, PullRequestsListActivity::class.java)
            startActivity(intent)
        }
        binding?.rcListRepositories?.layoutManager =  LinearLayoutManager(this)
        binding?.rcListRepositories?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}