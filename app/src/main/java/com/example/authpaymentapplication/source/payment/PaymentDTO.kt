package com.example.authpaymentapplication.source.payment

import com.example.authpaymentapplication.app.model.payment.Payment

class PaymentDTO(
    val id: Int?,
    val title: String?,
    val amount: Any?,
    val created: Int?
) {
    fun toPayment(): Payment {
        val formattedAmount = formatAmount()
        val formattedDate = created ?: "N/A"
        return Payment(id, title ?: "", formattedAmount, formattedDate)
    }

    private fun formatAmount(): String {
        return when (amount) {
            is String -> {
                amount.ifEmpty { "0" }
            }
            is Number -> {
                val doubleAmount = (amount ).toDouble()
                if (doubleAmount % 1 == 0.0) {
                    String.format("%.0f", doubleAmount)
                } else {
                    String.format("%.2f", doubleAmount)
                }
            }
            else -> "N/A"
        }
    }

}

class PaymentResponse(
    val success: String,
    val response: List<PaymentDTO>
)

