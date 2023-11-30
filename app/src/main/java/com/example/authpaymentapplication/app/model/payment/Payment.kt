package com.example.authpaymentapplication.app.model.payment

data class Payment(
    val id: Int,
    val title: String,
    val amount: Int,
    val created: Int
)