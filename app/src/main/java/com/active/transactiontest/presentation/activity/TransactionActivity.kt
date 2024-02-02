package com.active.transactiontest.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.active.transactiontest.R
import com.active.transactiontest.databinding.ActivityTransactionBinding
import com.active.transactiontest.presentation.viewmodel.TransactionSharedViewModel
import kotlinx.coroutines.launch

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTransactionBinding
    private lateinit var navController: NavController
    private lateinit var sharedViewModel: TransactionSharedViewModel
    private var addTransactionItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this)[TransactionSharedViewModel::class.java]

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        observeValues()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.transaction_menu, menu)
        addTransactionItem = menu.findItem(R.id.action_add)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                navController.navigate(
                    resId = R.id.action_ListFragment_to_AddFragment
                )
                true
            }
            R.id.action_logout -> {
                goToActivityIntent(LoginActivity::class.java)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeValues() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.addEnable.collect { addEbable ->
                    addTransactionItem?.let {
                        it.isEnabled = addEbable
                    }
                }
            }
        }
    }
}
