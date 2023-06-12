package me.emmanuel.resilience.domain.adapter

import me.emmanuel.resilience.domain.entity.Order

interface PaymentGateway {
    fun pay(order: Order)
}