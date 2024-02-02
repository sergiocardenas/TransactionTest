package com.active.transactiontest.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.active.transactiontest.presentation.screen.LoginScreen
import com.active.transactiontest.presentation.viewmodel.LoginViewModel
import com.active.transactiontest.ui.theme.TransactionTestTheme
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContent {
            TransactionTestTheme {
                LoginScreen (viewModel::saveLogin)
            }
        }
        observeSession()
    }

    private fun observeSession() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.hasSession.collect { hasSession ->
                    if (hasSession) {
                        goToActivityIntent(TransactionActivity::class.java)
                        finish()
                    }
                }
            }
        }
    }
}
