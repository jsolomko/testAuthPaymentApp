package com.example.authpaymentapplication.app.ui.payment

import androidx.lifecycle.ViewModel
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.payment.PaymentRepository

class PaymentViewModel(
    val paymentRepository: PaymentRepository= Singletons.paymentRepository
): ViewModel() {
}