package com.active.transactiontest.presentation.activity

import android.app.Activity
import android.content.Intent

fun Activity.goToActivityIntent(cl: Class<*>?) {
    val intent = Intent(this, cl)
    startActivity(intent)
}
