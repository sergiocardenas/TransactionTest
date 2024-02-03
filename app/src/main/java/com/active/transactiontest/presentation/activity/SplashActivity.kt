package com.active.transactiontest.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.active.transactiontest.presentation.screen.SplashScreen
import com.active.transactiontest.presentation.viewmodel.SplashViewModel
import com.active.transactiontest.ui.theme.TransactionTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect { isLoading ->
                    if (!isLoading) {
                        goToActivityIntent(
                            if(viewModel.hasSession()) TransactionActivity::class.java
                            else LoginActivity::class.java
                        )
                        finish()
                    }
                }
            }
        }
    }
}
