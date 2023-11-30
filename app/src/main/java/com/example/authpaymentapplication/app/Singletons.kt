package com.example.authpaymentapplication.app

import android.content.Context
import com.example.authpaymentapplication.app.model.SourceProvider
import com.example.authpaymentapplication.app.model.auth.AuthRepository
import com.example.authpaymentapplication.app.model.auth.AuthSource
import com.example.authpaymentapplication.app.model.payment.PaymentRepository
import com.example.authpaymentapplication.app.model.payment.PaymentSource
import com.example.authpaymentapplication.app.setting.AppSettings
import com.example.authpaymentapplication.app.setting.SharedPrefSettings
import com.example.authpaymentapplication.source.SourceProviderHolder

object Singletons {
    private lateinit var appContext: Context

    private val sourceProvider: SourceProvider by lazy {
        SourceProviderHolder.retrofitSourceProvider
    }

    //sources
    private val authSource: AuthSource by lazy {
        sourceProvider.getAuthSource()
    }
    private val paymentSource: PaymentSource by lazy {
        sourceProvider.getPaymentSource()
    }

    //repositories
    val authRepository: AuthRepository by lazy {
        AuthRepository(authSource, appSettings)
    }
    val paymentRepository: PaymentRepository by lazy {
        PaymentRepository(paymentSource)
    }

    fun init(appContext: Context) {
        Singletons.appContext = appContext
    }

    val appSettings: AppSettings by lazy {
        SharedPrefSettings(appContext)
    }
}