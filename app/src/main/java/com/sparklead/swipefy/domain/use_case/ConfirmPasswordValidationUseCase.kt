package com.sparklead.swipefy.domain.use_case

import com.sparklead.swipefy.domain.model.ValidationResult

class ConfirmPasswordValidationUseCase {

    operator fun invoke(password: String, confirmPassword: String): ValidationResult {
        return if (password != confirmPassword)
            ValidationResult(
                success = false,
                message = "Password don't match"
            )
        else ValidationResult(success = true)
    }
}