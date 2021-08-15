package com.ruchij.services.health.models

import java.util.*

data class ServiceInformation(
    val serviceName: String,
    val serviceVersion: String,
    val javaVersion: String,
    val currentTimestamp: Date,
    val gitBranch: String?,
    val gitCommit: String?,
    val buildTimestamp: Date?
)
