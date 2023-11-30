package com.example.authpaymentapplication.app.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.auth.AuthRepository
import com.example.authpaymentapplication.app.utils.ConnectionException
import com.example.authpaymentapplication.app.utils.EmptyFieldException
import com.example.authpaymentapplication.app.utils.InvalidCredentialsException
import com.example.authpaymentapplication.app.utils.share
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = Singletons.authRepository
) : ViewModel() {

    private val _navigateToPayment = MutableLiveData<Boolean>()
    val navigateToPayment = _navigateToPayment.share()

    private var _auth = MutableLiveData<Boolean>()
    val auth = _auth.share()

    fun login(login: String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.login(login, password)
                launchPaymentScreen()
            } catch (e: EmptyFieldException) {
                processEmptyFieldException(e)
            } catch (e: InvalidCredentialsException) {
                processInvalidCredentialsException()
            } catch (e: ConnectionException) {
                processConnectionException(e)
            }

        }

    }

    private fun processConnectionException(e: ConnectionException) {
        TODO("Not yet implemented")
    }

    private fun processInvalidCredentialsException() {
        TODO("Not yet implemented")
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        TODO("Not yet implemented")
    }

    private fun launchPaymentScreen() {
        _navigateToPayment.value = true
    }

}