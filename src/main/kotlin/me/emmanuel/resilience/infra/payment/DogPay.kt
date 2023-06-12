package me.emmanuel.resilience.infra.payment

import me.emmanuel.resilience.domain.adapter.PaymentGateway
import me.emmanuel.resilience.domain.entity.Order
import me.emmanuel.resilience.infra.http.client.DogPayHttpClient
import org.springframework.stereotype.Component

@Component
class DogPay(
    private val dogPayHttpClient: DogPayHttpClient
) : PaymentGateway {

    override fun pay(order: Order) {
        println("Dog pay")
        dogPayHttpClient.pay()
    }
}