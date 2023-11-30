package com.example.authpaymentapplication.app.model.auth

import com.example.authpaymentapplication.app.setting.AppSettings
import com.example.authpaymentapplication.app.utils.BackendException
import com.example.authpaymentapplication.app.utils.EmptyFieldException
import com.example.authpaymentapplication.app.utils.Field
import com.example.authpaymentapplication.app.utils.InvalidCredentialsException
import com.example.authpaymentapplication.source.auth.TokenResponseDTO

class AuthRepository(
    private val authSource: AuthSource,
    private val settings: AppSettings
) {
    suspend fun login(login: String, password: String) {

        if (login.isBlank()) throw EmptyFieldException(Field.Login)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        val token = try {
            authSource.login(Auth(login, password))
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                throw InvalidCredentialsException(e)
            } else
                throw e
        }
        settings.setCurrentToken(token.response.token)
    }
}