package com.example.authpaymentapplication.source

import com.example.authpaymentapplication.app.BASE_URL
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.SourceProvider
import com.example.authpaymentapplication.app.setting.AppSettings
import com.example.authpaymentapplication.source.base.RetrofitConfig
import com.example.authpaymentapplication.source.base.RetrofitSourceProvider
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SourceProviderHolder {


    val retrofitSourceProvider: SourceProvider by lazy {
        val moshi = Moshi.Builder().build()
        val config = RetrofitConfig(
            creteRetrofit(moshi), moshi
        )
        RetrofitSourceProvider(config)
    }


    private fun creteRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHHtpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun createOkHHtpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor(Singletons.appSettings))
            .addInterceptor(createAuthorizationInterceptor())
            .build()
    }

    private fun createAuthorizationInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun createLoggingInterceptor(appSettings: AppSettings): Interceptor {
        return Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            val token = appSettings.getCurrentToken()
            newBuilder.addHeader("app-key","12345")
            newBuilder.addHeader("v","1")
            if (token != null) {
                newBuilder.addHeader("token", "$token")
            }
            return@Interceptor chain.proceed(newBuilder.build())
        }
    }

}