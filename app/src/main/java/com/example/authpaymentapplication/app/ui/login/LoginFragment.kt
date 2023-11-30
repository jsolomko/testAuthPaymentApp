package com.example.authpaymentapplication.app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authpaymentapplication.R
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
            onLoginButtonPressed()
        }

        observeState()
        observeNavigateToPaymentEvent()
        observeShowAuthErrorMessageEvent()
        observeShowConnectionErrorMessageEvent()
        return binding.root
    }

    private fun onLoginButtonPressed() {
        val login = binding.editLogin.text
        val password = binding.editPassword.text
        viewModel.login(login.toString(), password.toString())
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) {
        binding.textInpitLogin.error =
            if (it.emptyLoginError) getString(R.string.field_is_empty) else null
        binding.textInputPaassword.error =
            if (it.emptyPasswordError) getString(R.string.field_is_empty) else null
    }

    private fun observeShowAuthErrorMessageEvent() =
        viewModel.showAuthToastEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    private fun observeShowConnectionErrorMessageEvent() =
        viewModel.showConnectionErrorToastEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    private fun observeNavigateToPaymentEvent() =
        viewModel.navigateToPayment.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(R.id.action_loginFragment_to_paymentFragment)
        }
}