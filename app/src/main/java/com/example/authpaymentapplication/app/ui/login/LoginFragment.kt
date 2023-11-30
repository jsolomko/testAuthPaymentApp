package com.example.authpaymentapplication.app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authpaymentapplication.R
import com.example.authpaymentapplication.app.model.auth.Auth
import com.example.authpaymentapplication.app.utils.ViewModelFactory
import com.example.authpaymentapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels { ViewModelFactory() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            val login = binding.editLogin.text
            val password = binding.editPassword.text
            viewModel.login(Auth(login.toString(), password.toString()))
            findNavController().navigate(R.id.action_loginFragment_to_paymentFragment)
        }

        return binding.root
    }

}