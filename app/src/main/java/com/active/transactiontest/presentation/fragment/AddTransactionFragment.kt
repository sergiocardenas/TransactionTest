package com.active.transactiontest.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.active.transactiontest.R
import com.active.transactiontest.presentation.screen.AddTransactionScreen
import com.active.transactiontest.presentation.screen.SplashScreen
import com.active.transactiontest.presentation.viewmodel.TransactionAddViewModel
import com.active.transactiontest.presentation.viewmodel.TransactionSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

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
    }

    fun validateNewTransaction(value: Int, concept: String, withdraw: Boolean){
        viewModel.addNewTransaction(value, concept,withdraw)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.finishAdding.collect{ result ->
                    if (result){
                        goToListTransactionFrag()
                    }
                }
            }
        }
    }

    private fun goToListTransactionFrag(){
        sharedViewModel.setRefresh(true)
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}