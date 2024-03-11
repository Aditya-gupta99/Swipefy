package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.model.ValidationResult

class NameValidationUseCase {

    operator fun invoke(name: String): ValidationResult {
        return if (name.isBlank())
            ValidationResult(
                success = false,
                message = "Name can't be blank"
            )
        else ValidationResult(success = true)
    }
}