package com.example.authpaymentapplication.app.utils

open class AppException : RuntimeException {
    constructor() : super()
    constructor(cause: Throwable) : super(cause)
    constructor(message: String) : super(message)
}

class InvalidCredentialsException(cause: Exception) : AppException(cause = cause)
class EmptyFieldException(val field: Field) : AppException()
class ParseBackendResponseException(cause: Throwable) : AppException(cause = cause)
class PasswordMismatchException : AppException()
class ConnectionException(cause: Throwable) : AppException(cause)
class BackendException(
    message: String,
    val code: Int,
) : AppException(message)

enum class Field {
    Login,
    Password
}



