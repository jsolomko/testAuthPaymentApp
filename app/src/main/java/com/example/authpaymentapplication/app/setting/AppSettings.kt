package com.example.authpaymentapplication.app.setting

interface AppSettings {
    fun getCurrentToken(): String?
    fun setCurrentToken(token: String?)
}