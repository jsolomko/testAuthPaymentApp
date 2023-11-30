package com.example.authpaymentapplication.source.auth

import android.media.session.MediaSession.Token
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body authDTO: AuthDTO): TokenResponseDTO
}