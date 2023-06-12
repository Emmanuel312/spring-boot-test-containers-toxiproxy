package me.emmanuel.resilience.integration.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration
class DogPayContainerConfiguration {

    companion object {
        val wiremock = GenericContainer<Nothing>(DockerImageName.parse("wiremock/wiremock")).apply {
            withExposedPorts(8080)
            withFileSystemBind(
                "./local/wiremock",
                "/home/wiremock/mappings",
                BindMode.READ_ONLY
            )
            start()
        }

        private fun getWireMockHost() = "http://${wiremock.host}:${wiremock.getMappedPort(8080)}"

        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.cloud.openfeign.client.config.dog-pay.url") { getWireMockHost() }
        }
    }
}