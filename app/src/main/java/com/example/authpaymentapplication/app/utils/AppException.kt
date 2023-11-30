package com.example.authpaymentapplication.app.utils

import android.os.Message

open class AppException : RuntimeException {
    constructor() : super()
    constructor(cause: Throwable) : super(cause)
    constructor(message: String) : super(message)
}

class InvalidCredentialsException(cause: Exception) : AppException(cause = cause)
class EmptyFieldException(field: Field) : AppException()
class ParseBackendResponseException(cause: Throwable) : AppException(cause = cause)
class AppConnectionException(cause: Throwable) : AppException(cause)
class PasswordMismatchException : AppException()
class ConnectionException(cause: Throwable) : AppException(cause)
class BackendException(
    val code: Int,
    message: String
) : AppException(message)

enum class Field {
    Login,
    Password
}



