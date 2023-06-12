package me.emmanuel.resilience.infra.http.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "cat-pay"
)
interface CatPayHttpClient {

    @PostMapping("/pay")
    fun pay()
}