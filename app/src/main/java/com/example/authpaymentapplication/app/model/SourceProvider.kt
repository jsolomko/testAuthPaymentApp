package com.example.authpaymentapplication.app.model

import com.example.authpaymentapplication.app.model.auth.AuthSource
import com.example.authpaymentapplication.app.model.payment.PaymentSource

interface SourceProvider {
    fun getAuthSource(): AuthSource
    fun getPaymentSource(): PaymentSource
}