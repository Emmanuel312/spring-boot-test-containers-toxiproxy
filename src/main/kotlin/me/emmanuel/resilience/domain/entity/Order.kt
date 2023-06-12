package me.emmanuel.resilience.domain.entity

import java.util.*

data class Order(
    val id: UUID,
    val value: Double
)