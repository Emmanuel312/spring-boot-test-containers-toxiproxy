package me.emmanuel.resilience

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class ResilienceApplication

fun main(args: Array<String>) {
    runApplication<ResilienceApplication>(*args)
}
