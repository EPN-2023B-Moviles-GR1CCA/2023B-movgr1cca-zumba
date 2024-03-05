package com.example.uber_conductor.models

class FCMBody(
    val to: String,
    val priority: String,
    val ttl: String,
    val data: MutableMap<String, String>,
) {
}