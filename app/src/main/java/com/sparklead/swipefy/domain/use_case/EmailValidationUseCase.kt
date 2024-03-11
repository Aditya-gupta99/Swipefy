package com.sparklead.swipefy.domain.use_case

import android.util.Patterns
import com.sparklead.core.data.model.ValidationResult

class EmailValidationUseCase {

    operator fun invoke(email: String): ValidationResult {
        return if (email.isBlank())
            ValidationResult(
                success = false,
                message = "Email can't be blank"
            )
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult(
                success = false,
                message = "That's not a valid email"
            )
        } else ValidationResult(success = true)

    }
}