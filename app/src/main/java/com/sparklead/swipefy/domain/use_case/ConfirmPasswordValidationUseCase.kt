package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.model.ValidationResult

class ConfirmPasswordValidationUseCase {

    operator fun invoke(password: String, confirmPassword: String): com.sparklead.core.data.model.ValidationResult {
        return if (password != confirmPassword)
            com.sparklead.core.data.model.ValidationResult(
                success = false,
                message = "Password don't match"
            )
        else com.sparklead.core.data.model.ValidationResult(success = true)
    }
}