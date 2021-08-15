package com.ruchij.services.health

import com.ruchij.services.health.models.HealthCheck
import com.ruchij.services.health.models.HealthStatus
import com.ruchij.services.health.models.ServiceInformation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

@Service
class HealthServiceImpl(private val jdbcTemplate: JdbcTemplate): HealthService {
    override fun serviceInformation(): ServiceInformation {
        TODO("Not yet implemented")
    }

    override fun healthCheck(): HealthCheck {
        val timeout =
            CompletableFuture.supplyAsync {
                Thread.sleep(5_000)
                return@supplyAsync HealthStatus.Unhealthy
            }

        val database =
            CompletableFuture.supplyAsync {
                val result = jdbcTemplate.queryForObject("SELECT 1", Int::class.java)

                return@supplyAsync if (result == 1) HealthStatus.Healthy else HealthStatus.Unhealthy
            }

        val databaseResult: HealthStatus =
            CompletableFuture.anyOf(timeout, database).join() as? HealthStatus ?: HealthStatus.Unhealthy

        return HealthCheck(databaseResult)
    }
}