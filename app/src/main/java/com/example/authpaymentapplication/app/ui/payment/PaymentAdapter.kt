package com.example.authpaymentapplication.app.ui.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.authpaymentapplication.app.model.payment.Payment
import com.example.authpaymentapplication.databinding.ItemPaymentBinding

class PaymentAdapter(
    private val payments: List<Payment>
) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {
    class PaymentViewHolder(val binding: ItemPaymentBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val binding = ItemPaymentBinding.inflate(inflater, parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = payments[position]
        with(holder.binding) {
            textViewId.text = payment.id.toString()
            textViewTitle.text = payment.title
            textViewAmount.text = payment.amount.toString()
            textViewCreated.text = payment.created.toString()
        }

    }

    override fun getItemCount(): Int = payments.size
}