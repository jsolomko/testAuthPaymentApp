package com.example.authpaymentapplication.app.utils

class AppException : RuntimeException()

class EmptyFieldException(
    e: String
) : RuntimeException(e)

enum class Field{
    Login,
    Password
}