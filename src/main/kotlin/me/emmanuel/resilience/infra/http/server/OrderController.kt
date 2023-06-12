package me.emmanuel.resilience.infra.http.server

import me.emmanuel.resilience.domain.entity.Order
import me.emmanuel.resilience.domain.useCase.ProcessOrderUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.random.Random

@RestController
@RequestMapping("order")
class OrderController(
    private val processOrderUseCase: ProcessOrderUseCase
) {

    @PostMapping
    fun process() {
        val order = Order(
            id = UUID.randomUUID(),
            value = Random.nextDouble(500.0)
        )

        processOrderUseCase.process(order)
    }
}