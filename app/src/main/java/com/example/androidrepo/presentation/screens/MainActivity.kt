package com.example.androidrepo.presentation.screens

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.androidrepo.databinding.ActivityMainBinding
import com.example.androidrepo.domain.model.Items
import com.example.androidrepo.presentation.screens.repositories.RepositoriesListActivity
import com.example.androidrepo.presentation.screens.repositories.viewmodel.RepositoriesListViewModel
import com.example.androidrepo.utils.common.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: RepositoriesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.getIsReady()) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
        val intent = Intent(this, RepositoriesListActivity::class.java)
        startActivity(intent)
        finish()
        setContentView(binding.root)
    }
}