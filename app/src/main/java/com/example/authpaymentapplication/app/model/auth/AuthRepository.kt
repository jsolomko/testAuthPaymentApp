package com.example.authpaymentapplication.app.model.auth

import com.example.authpaymentapplication.app.setting.AppSettings
import com.example.authpaymentapplication.app.utils.*
import com.example.authpaymentapplication.source.auth.TokenResponse
import com.example.authpaymentapplication.source.auth.TokenResponseDTO


class AuthRepository(
    private val authSource: AuthSource,
    private val settings: AppSettings
) {
    suspend fun login(login: String, password: String) {
        if (login.isBlank()) throw EmptyFieldException(Field.Login)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        val token = try {
            val response = authSource.login(Auth(login, password))
            if (response.success == "false") {
                throw InvalidCredentialsException(Exception())
            } else authSource.login(Auth(login, password)).response
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                throw InvalidCredentialsException(e)
            } else
                throw e
        }
        settings.setCurrentToken(token.token)
    }
}