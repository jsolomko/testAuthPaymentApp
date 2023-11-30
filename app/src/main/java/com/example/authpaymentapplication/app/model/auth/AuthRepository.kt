package com.example.authpaymentapplication.app.model.auth

import com.example.authpaymentapplication.app.setting.AppSettings
import com.example.authpaymentapplication.source.auth.TokenResponseDTO

class AuthRepository(
    private val authSource: AuthSource,
    private val settings: AppSettings
) {
    suspend fun login(auth: Auth) {
        val token: TokenResponseDTO = authSource.login(auth)
        settings.setCurrentToken(token.response.token)
    }
}