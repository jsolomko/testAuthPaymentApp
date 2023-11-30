package com.example.authpaymentapplication.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.authpaymentapplication.app.ui.login.LoginViewModel
import com.example.authpaymentapplication.app.ui.payment.PaymentViewModel

fun <T> MutableLiveData<T>.share(): LiveData<T> = this

@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel() as T
            PaymentViewModel::class.java -> PaymentViewModel() as T
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}