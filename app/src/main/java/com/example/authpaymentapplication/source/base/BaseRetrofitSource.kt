package com.example.authpaymentapplication.source.base

import com.example.authpaymentapplication.app.utils.AppConnectionException
import com.example.authpaymentapplication.app.utils.AppException
import com.example.authpaymentapplication.app.utils.BackendException
import com.example.authpaymentapplication.app.utils.ParseBackendResponseException
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

open class BaseRetrofitSource(
    config: RetrofitConfig
) {
    val retrofit: Retrofit = config.retrofit
    val moshi = config.moshi

    private val errorAdapter = config.moshi.adapter(ErrorResponseBody::class.java)

    suspend fun <T> wrapRetrofitException(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: AppException) {
            throw e
        } catch (e: JsonDataException) {
            throw ParseBackendResponseException(e)
        } catch (e: HttpException) {
            throw createBackendException(e)
        } catch (e: IOException) {
            throw AppConnectionException(e)
        }
    }

    private fun createBackendException(e: HttpException): Exception {
        return try {
            val errorBody: ErrorResponseBody = errorAdapter.fromJson(
                e.response()!!.errorBody()!!.string()
            )!!
            BackendException(e.code(), errorBody.error.toString())
        } catch (e: Exception) {
            throw ParseBackendResponseException(e)
        }
    }

    class ErrorResponseBody(
        val error: Boolean
    )
}