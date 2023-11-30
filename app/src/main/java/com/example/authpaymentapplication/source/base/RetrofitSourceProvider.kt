package com.example.authpaymentapplication.source.base

import com.example.authpaymentapplication.app.model.SourceProvider
import com.example.authpaymentapplication.app.model.auth.AuthSource
import com.example.authpaymentapplication.app.model.payment.PaymentSource
import com.example.authpaymentapplication.source.auth.RetrofitAuthSource
import com.example.authpaymentapplication.source.payment.RetrofitPaymentSource

class RetrofitSourceProvider(val config: RetrofitConfig) :SourceProvider{

    override fun getAuthSource(): AuthSource {
        return RetrofitAuthSource(config)
    }

    override fun getPaymentSource(): PaymentSource {
        return RetrofitPaymentSource(config)
    }

}