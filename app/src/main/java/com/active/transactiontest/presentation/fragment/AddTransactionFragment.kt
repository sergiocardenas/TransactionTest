package com.active.transactiontest.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.active.transactiontest.R
import com.active.transactiontest.presentation.screen.AddTransactionScreen
import com.active.transactiontest.presentation.screen.SplashScreen
import com.active.transactiontest.presentation.viewmodel.TransactionAddViewModel
import com.active.transactiontest.presentation.viewmodel.TransactionSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment: Fragment() {
    private lateinit var viewModel: TransactionAddViewModel
    private lateinit var sharedViewModel: TransactionSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionAddViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[TransactionSharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent {
           AddTransactionScreen(onCreateClick = this::validateNewTransaction)
        }
        return composeView
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.desableAddButton()
    }

    fun validateNewTransaction(value: Int, concept: String, withdraw: Boolean){
        val result = viewModel.addNewTransaction(value, concept,withdraw)
        if(result){
            goToListTransactionFrag()
        }
    }

    private fun goToListTransactionFrag(){
        sharedViewModel.setRefresh(true)
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}