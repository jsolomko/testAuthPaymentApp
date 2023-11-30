package com.example.authpaymentapplication.source.base

import retrofit2.Retrofit

open class BaseRetrofitSource(
    config: RetrofitConfig
) {
    val retrofit: Retrofit = config.retrofit
    val moshi = config.moshi
}