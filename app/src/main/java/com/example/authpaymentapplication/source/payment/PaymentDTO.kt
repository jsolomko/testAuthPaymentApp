package com.example.authpaymentapplication.source.payment

import com.example.authpaymentapplication.app.model.payment.Payment

class PaymentDTO(
    val id: Int,
    val title: String,
    val amount: Int,
    val created: Int
) {
    fun toPayment() = Payment(
        id = id,
        title = title,
        amount = amount,
        created = created
    )
}

class PaymentResponse(
    val success: Boolean,
    val response: List<PaymentDTO>
)