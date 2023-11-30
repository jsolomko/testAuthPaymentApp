package com.example.authpaymentapplication.app.model.auth

import com.example.authpaymentapplication.source.auth.TokenResponseDTO

interface AuthSource {
    suspend fun login(auth: Auth): TokenResponseDTO
}