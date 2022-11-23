package com.example.myapplication11.models

data class AuditInfo(
    val auditStatus: Double,
    val auditTime: String,
    val auditor: String,
    val coinId: String,
    val contractAddress: String,
    val contractPlatform: String,
    val reportUrl: String,
    val score: String
)