package com.example.authpaymentapplication.app.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authpaymentapplication.R
import com.example.authpaymentapplication.app.ui.login.LoginViewModel
import com.example.authpaymentapplication.app.utils.ViewModelFactory
import com.example.authpaymentapplication.databinding.FragmentPaymentBinding

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

        return binding.root
    }
}