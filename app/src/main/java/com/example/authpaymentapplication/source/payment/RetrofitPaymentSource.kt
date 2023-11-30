package com.example.authpaymentapplication.source.payment

import com.example.authpaymentapplication.app.model.payment.Payment
import com.example.authpaymentapplication.app.model.payment.PaymentSource
import com.example.authpaymentapplication.app.utils.wrapBackendExceptions
import com.example.authpaymentapplication.source.base.BaseRetrofitSource
import com.example.authpaymentapplication.source.base.RetrofitConfig


class RetrofitPaymentSource(config: RetrofitConfig) : BaseRetrofitSource(config), PaymentSource {

    private val api = config.retrofit.create(PaymentApi::class.java)

    override suspend fun getPayments(): List<Payment> = wrapRetrofitException {
        api.getPayments().response.map { paymentDTO ->
            paymentDTO.toPayment()
        }
    }

}