package com.example.authpaymentapplication.app.ui.payment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.authpaymentapplication.R
import com.example.authpaymentapplication.app.model.payment.Payment
import com.example.authpaymentapplication.databinding.ItemPaymentBinding

class PaymentAdapter(
    val context: Context,
    private val payments: List<Payment>
) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {
    class PaymentViewHolder(val binding: ItemPaymentBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val binding = ItemPaymentBinding.inflate(inflater, parent, false)
        return PaymentViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = payments[position]
        with(holder.binding) {
            textViewId.text = context.getString(R.string.payment_id, payment.id.toString())
            textViewTitle.text = context.getString(R.string.payment_title, payment.title)
            textViewAmount.text =  context.getString(R.string.payment_amount, payment.amount)
            textViewCreated.text =context.getString(R.string.payment_created_at, payment.created)
        }

    }

    override fun getItemCount(): Int = payments.size
}