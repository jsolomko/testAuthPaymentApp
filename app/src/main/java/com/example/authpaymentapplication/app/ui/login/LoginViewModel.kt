package com.example.authpaymentapplication.app.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authpaymentapplication.app.Singletons
import com.example.authpaymentapplication.app.model.auth.Auth
import com.example.authpaymentapplication.app.model.auth.AuthRepository
import com.example.authpaymentapplication.app.utils.share
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = Singletons.authRepository
) : ViewModel() {

    private var _auth = MutableLiveData<Boolean>()
    val auth = _auth.share()

    fun login(auth: Auth) {
        viewModelScope.launch {
            authRepository.login(auth)
        }

    }

}