package me.emmanuel.resilience.infra.payment

import me.emmanuel.resilience.domain.adapter.PaymentGateway
import me.emmanuel.resilience.domain.entity.Order
import me.emmanuel.resilience.infra.http.client.CatPayHttpClient
import org.springframework.stereotype.Component

@Component
class CatPay(
    private val catPayHttpClient: CatPayHttpClient
) : PaymentGateway {

    override fun pay(order: Order) {
        println("Cat pay!")
        catPayHttpClient.pay()
    }
}