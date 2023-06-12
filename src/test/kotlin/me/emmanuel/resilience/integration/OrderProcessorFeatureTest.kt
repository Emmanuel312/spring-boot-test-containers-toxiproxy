package me.emmanuel.resilience.integration

import eu.rekawek.toxiproxy.model.ToxicDirection
import me.emmanuel.resilience.integration.config.CatPayWithToxiProxyContainerConfiguration
import me.emmanuel.resilience.integration.config.IntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OrderProcessorFeatureTest(
    @Autowired
    private val mockMvc: MockMvc,
) : IntegrationTest() {

    @Test
    fun `It should call the fallback payment gateway if the primary one is unreachable`() {
        CatPayWithToxiProxyContainerConfiguration.catPayProxy.toxics()
            .bandwidth("CUT_CONNECTION_DOWNSTREAM", ToxicDirection.DOWNSTREAM, 0)
        CatPayWithToxiProxyContainerConfiguration.catPayProxy.toxics()
            .bandwidth("CUT_CONNECTION_UPSTREAM", ToxicDirection.UPSTREAM, 0)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
    }
}
