package com.example.androidrepo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidrepo.domain.model.pulls.PullRequests
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPullRequests("jetbrains", repo = "kotlin").enqueue(object : Callback<List<PullRequests>> {
            override fun onResponse(
                call: Call<List<PullRequests>>,
                response: Response<List<PullRequests>>
            ) {
                if (response.isSuccessful) {
                    println("Secusso na requisição: ${response.body()}")
                    val textView = findViewById<TextView>(R.id.list)
                    textView.text = response.body()?.toString()
                } else {
                    println("Erro na resposta: ${response.code()} , ${
                        response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<List<PullRequests>>, error: Throwable) {
                println("Falha na requisição: ${error.message}")
            }

        })

//        viewModel.getRepositories().enqueue(object : Callback<Repositories> {
//            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
//                if (response.isSuccessful) {
//                    println("Secusso na requisição: ${response.body()}")
//                    val textView = findViewById<TextView>(R.id.list)
//                    textView.text = response.body()?.items.toString()
//                } else {
//                    println("Erro na resposta: ${response.code()} , ${
//                        response.errorBody().toString()}")
//                }
//            }
//
//            override fun onFailure(call: Call<Repositories>, error: Throwable) {
//                println("Falha na requisição: ${error.message}")
//            }
//        })

        setContentView(R.layout.acitivity_main)
    }
}