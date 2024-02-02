package com.active.transactiontest.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.active.transactiontest.presentation.screen.SplashScreen
import com.active.transactiontest.presentation.viewmodel.SplashViewModel
import com.active.transactiontest.ui.theme.TransactionTestTheme

class SplashActivity : ComponentActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        setContent {
            TransactionTestTheme {
                SplashScreen()
            }
        }
        observeLoading()
        viewModel.splashTimer()
    }

    private fun observeLoading() {
        lifecycleScope.launchWhenStarted {
            viewModel.loading.collect { isLoading ->
                if (!isLoading) {
                    goToLoginActivity()
                    finish()
                }
            }
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}