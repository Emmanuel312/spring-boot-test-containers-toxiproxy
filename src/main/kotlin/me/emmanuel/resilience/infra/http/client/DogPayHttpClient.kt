package me.emmanuel.resilience.infra.http.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "dog-pay"
)
interface DogPayHttpClient {

    @PostMapping("/pay")
    fun pay()
}