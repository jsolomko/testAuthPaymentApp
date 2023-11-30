package com.example.authpaymentapplication.app.model.payment

class PaymentRepository(
    private val paymentSource: PaymentSource
) {
    suspend fun getPayment(): List<Payment> {
        return paymentSource.getPayments()
    }
}