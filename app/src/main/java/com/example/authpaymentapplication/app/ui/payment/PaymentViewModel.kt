package com.example.authpaymentapplication.app.ui.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.payment.Payment
import com.example.authpaymentapplication.app.model.payment.PaymentRepository
import com.example.authpaymentapplication.app.utils.EmptyFieldException
import com.example.authpaymentapplication.app.utils.PasswordMismatchException
import com.example.authpaymentapplication.app.utils.share
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.launch

class PaymentViewModel(
    val paymentRepository: PaymentRepository = Singletons.paymentRepository
) : ViewModel() {

    private var _payment = MutableLiveData<List<Payment>>()
    val payment = _payment.share()

    fun getPayments() {
        viewModelScope.launch {
            try {
                _payment.value = paymentRepository.getPayment()
            } catch (e: EmptyFieldException) {
                processEmptyFieldException(e)
            } catch (e: PasswordMismatchException) {
                processPasswordMismatchException()
            } catch (e: JsonDataException) {
                processJsonDataException(e)
            }

        }

    }

    private fun processJsonDataException(e: JsonDataException) {
        println("processJsonDataException ${payment.value?.size} ")
        println(e)
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {

    }

    private fun processPasswordMismatchException() {

    }
}