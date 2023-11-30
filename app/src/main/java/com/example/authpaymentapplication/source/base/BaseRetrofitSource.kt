package com.example.authpaymentapplication.source.base

import com.example.authpaymentapplication.app.utils.*
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

open class BaseRetrofitSource(
    config: RetrofitConfig
) {
    val retrofit: Retrofit = config.retrofit
    val moshi = config.moshi

    private val errorAdapter = moshi.adapter(ErrorResponseBody::class.java)

    suspend fun <T> wrapRetrofitException(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: AppException) {
            throw e
            // moshi
        } catch (e: JsonDataException) {
            throw ParseBackendResponseException(e)
        } catch (e: JsonEncodingException) {
            throw ParseBackendResponseException(e)
            // retrofit
        } catch (e: HttpException) {
            throw createBackendException(e)
            // retrofit
        } catch (e: IOException) {
            throw ConnectionException(e)
        }
    }

    private fun createBackendException(e: HttpException): Exception {
        return try {
            val errorBody: ErrorResponseBody = errorAdapter.fromJson(
                e.response()!!.errorBody()!!.string()
            )!!
            BackendException(errorBody.success.toString(), e.code())
        } catch (e: Exception) {
            throw ParseBackendResponseException(e)
        }
    }

    class ErrorResponseBody(
        val success: Boolean,
        val error: Error
    )

    class Error(
        error_code: Int,
        error_msg: String
    )
}