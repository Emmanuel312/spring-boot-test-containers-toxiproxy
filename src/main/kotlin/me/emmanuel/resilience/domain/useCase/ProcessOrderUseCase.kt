package me.emmanuel.resilience.domain.useCase

import me.emmanuel.resilience.domain.adapter.PaymentGateway
import me.emmanuel.resilience.domain.entity.Order
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ProcessOrderUseCase(
    @Qualifier("catPay")
    private val catPay: PaymentGateway,
    @Qualifier("dogPay")
    private val dogPay: PaymentGateway,
) {

    fun process(order: Order) {
        try {
            catPay.pay(order)
        } catch (ex: Exception) {
            println(ex)
            dogPay.pay(order)
        }
    }
}