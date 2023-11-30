package com.example.authpaymentapplication.app.ui.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.payment.Payment
import com.example.authpaymentapplication.app.model.payment.PaymentRepository
import com.example.authpaymentapplication.app.utils.share
import kotlinx.coroutines.launch

class PaymentViewModel(
    val paymentRepository: PaymentRepository = Singletons.paymentRepository
) : ViewModel() {

    private var _payment = MutableLiveData<List<Payment>>()
    val payment = _payment.share()

    fun getPayments() {
        viewModelScope.launch {
            _payment.value = paymentRepository.getPayment()
        }

    }
}