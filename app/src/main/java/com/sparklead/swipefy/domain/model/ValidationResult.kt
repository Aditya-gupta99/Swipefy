package com.sparklead.swipefy.domain.model

data class ValidationResult(
    val success: Boolean,
    val message: String? = null
)
