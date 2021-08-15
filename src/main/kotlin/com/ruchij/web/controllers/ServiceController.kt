package com.ruchij.web.controllers

import com.ruchij.services.health.HealthService
import com.ruchij.services.health.models.HealthCheck
import com.ruchij.services.health.models.ServiceInformation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service")
class ServiceController(private val healthService: HealthService) {

    @GetMapping("/info")
    fun serviceInformation(): ServiceInformation {
        TODO()
    }

    @GetMapping("/health-check")
    fun healthCheck(): HealthCheck = healthService.healthCheck()

}