package com.example.authpaymentapplication.app.ui.payment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authpaymentapplication.R
import com.example.authpaymentapplication.app.ui.MainActivity
import com.example.authpaymentapplication.app.ui.login.LoginViewModel
import com.example.authpaymentapplication.app.utils.ViewModelFactory
import com.example.authpaymentapplication.databinding.FragmentPaymentBinding
import kotlin.math.log

class PaymentFragment : Fragment(R.layout.fragment_payment) {
    lateinit var binding: FragmentPaymentBinding
    private val viewModel: PaymentViewModel by viewModels { ViewModelFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        val recycler = binding.rvPayment.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        viewModel.getPayments()
        viewModel.payment.observe(viewLifecycleOwner) {
            println(it.size)
            recycler.adapter = PaymentAdapter(it)
        }
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_logout -> {
                        logout()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun logout() {
        viewModel.logout()
        restartWithSingIn()
    }


    private fun restartWithSingIn() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}