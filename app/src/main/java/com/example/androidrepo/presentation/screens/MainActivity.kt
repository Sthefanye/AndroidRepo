package com.example.androidrepo.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidrepo.databinding.ActivityMainBinding
import com.example.androidrepo.presentation.screens.repositories.RepositoriesListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(this, RepositoriesListActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)


        setContentView(binding.root)
    }
}