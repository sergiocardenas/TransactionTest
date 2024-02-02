package com.active.transactiontest.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.active.transactiontest.presentation.screen.SplashScreen
import com.active.transactiontest.presentation.viewmodel.TransactionListViewModel
import com.active.transactiontest.presentation.viewmodel.TransactionSharedViewModel

class TransactionListFragment: Fragment() {
    private lateinit var viewModel: TransactionListViewModel
    private lateinit var sharedViewModel: TransactionSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionListViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[TransactionSharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            SplashScreen()
        }
        return composeView
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.enableAddButton()
    }
}