package com.example.authpaymentapplication.source.auth

import com.example.authpaymentapplication.app.model.auth.Auth
import com.example.authpaymentapplication.app.model.auth.AuthSource
import com.example.authpaymentapplication.source.base.BaseRetrofitSource
import com.example.authpaymentapplication.source.base.RetrofitConfig

class RetrofitAuthSource(config: RetrofitConfig) : BaseRetrofitSource(config), AuthSource {
    private val api = retrofit.create(AuthApi::class.java)
    override suspend fun login(auth: Auth): TokenResponseDTO = wrapRetrofitException {
        api.login(authDTO = AuthDTO(auth.login, auth.password))
    }
}