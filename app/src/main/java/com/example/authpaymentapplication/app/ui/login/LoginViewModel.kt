package com.example.authpaymentapplication.app.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.auth.AuthRepository
import com.example.authpaymentapplication.app.utils.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = Singletons.authRepository
) : ViewModel() {

    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _navigateToPayment = MutableLiveData<Boolean>(false)
    val navigateToPayment = _navigateToPayment.share()

    private val _showAuthErrorToastEvent = MutableLiveData<String>()
    val showAuthToastEvent = _showAuthErrorToastEvent.share()

    private var _auth = MutableLiveData<Boolean>()
    val auth = _auth.share()
    private val _showConnectionErrorToastEvent = MutableLiveData<String>()
    val showConnectionErrorToastEvent = _showConnectionErrorToastEvent.share()

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
        showConnectionErrorToast()
    }

    private fun processInvalidCredentialsException() {
        showAuthErrorToast()
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = _state.value?.copy(
            emptyLoginError = e.field == Field.Login,
            emptyPasswordError = e.field == Field.Password
        )
    }

    private fun showAuthErrorToast() {
        _showAuthErrorToastEvent.value = "Неверный логин или пароль"
    }

    private fun showConnectionErrorToast() {
        _showConnectionErrorToastEvent.value = "Ошибка соединения"
    }

    private fun launchPaymentScreen() {
        _navigateToPayment.value = true
    }

    data class State(
        val emptyLoginError: Boolean = false,
        val emptyPasswordError: Boolean = false,
    )
}