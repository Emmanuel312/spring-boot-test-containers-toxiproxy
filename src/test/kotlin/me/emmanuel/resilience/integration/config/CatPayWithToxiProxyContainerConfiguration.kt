package me.emmanuel.resilience.integration.config

import eu.rekawek.toxiproxy.ToxiproxyClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.ToxiproxyContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration
class CatPayWithToxiProxyContainerConfiguration {

    companion object {
        private val toxiNetwork = Network.newNetwork()
        private val wiremock = GenericContainer<Nothing>(DockerImageName.parse("wiremock/wiremock")).apply {
            withExposedPorts(8080)
            withFileSystemBind(
                "./local/wiremock",
                "/home/wiremock/mappings",
                BindMode.READ_ONLY
            )
            withNetwork(toxiNetwork)
            withNetworkAliases("cat")
            start()
        }
        val toxiproxy = ToxiproxyContainer("ghcr.io/shopify/toxiproxy:2.5.0").apply {
            withNetwork(toxiNetwork)
            start()
        }
        private val toxiClient = ToxiproxyClient(toxiproxy.host, toxiproxy.controlPort)
        val catPayProxy = toxiClient.createProxy("cat", "0.0.0.0:8666", "cat:8080")

        private fun getWireMockHost() = "http://${toxiproxy.host}:${
            toxiproxy.getMappedPort(8666)
        }"

        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.cloud.openfeign.client.config.cat-pay.url") { getWireMockHost() }
        }
    }
}