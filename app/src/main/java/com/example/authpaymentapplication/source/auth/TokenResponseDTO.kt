package com.example.authpaymentapplication.source.auth

class TokenResponseDTO(
    val success: String,
    val response: TokenResponse
)

class TokenResponse(
    val token: String
)
