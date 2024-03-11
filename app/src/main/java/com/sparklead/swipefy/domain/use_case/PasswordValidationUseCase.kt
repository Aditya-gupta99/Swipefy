package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.model.ValidationResult

class PasswordValidationUseCase {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                success = false,
                message = "Password needs to be consist of 8 characters"
            )
        }
        val containsLetterAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigits) {
            return ValidationResult(
                success = false,
                message = "Password must contains at least one letter and digit"
            )
        }
        return ValidationResult(success = true)
    }
}