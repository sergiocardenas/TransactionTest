package com.active.transactiontest.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.active.transactiontest.presentation.screen.LoginScreen
import com.active.transactiontest.presentation.viewmodel.LoginViewModel
import com.active.transactiontest.ui.theme.TransactionTestTheme

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
        lifecycleScope.launchWhenStarted {
            viewModel.hasSession.collect { hasSession ->
                if (hasSession) {
                    finish()
                }
            }
        }
    }

}