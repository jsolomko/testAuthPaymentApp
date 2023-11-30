package com.example.authpaymentapplication.app.model.payment

interface PaymentSource {
    suspend fun getPayments(): List<Payment>
}