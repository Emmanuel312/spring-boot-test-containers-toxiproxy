package me.emmanuel.resilience.integration.config

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(
    classes = [CatPayWithToxiProxyContainerConfiguration::class,
        DogPayContainerConfiguration::class]
)
abstract class IntegrationTest {

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            DogPayContainerConfiguration.registerProperties(registry)
            CatPayWithToxiProxyContainerConfiguration.registerProperties(registry)
        }
    }
}