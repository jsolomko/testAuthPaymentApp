package com.example.authpaymentapplication.source.payment

import retrofit2.http.GET

interface PaymentApi {
    @GET("payments")
    suspend fun getPayments(): PaymentResponse
}