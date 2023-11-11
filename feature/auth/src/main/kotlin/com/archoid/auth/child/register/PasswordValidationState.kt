package com.archoid.auth.child.register

data class PasswordValidationState(
	val isPasswordLengthValid: Boolean,
	val isSpecialCharExists: Boolean,
	val isDigitExists: Boolean,
	val isLatinCharsExists: Boolean
) {

	fun isFullValid() = this == fullValidState()

	companion object {
		fun PasswordValidationState.setValidationError(error: PasswordValidationError): PasswordValidationState =
			when(error) {
				PasswordValidationError.SHORT -> this.copy(
					isPasswordLengthValid = false
				)
				PasswordValidationError.SPECIAL_CHAR -> this.copy(
					isSpecialCharExists = false
				)
				PasswordValidationError.DIGIT -> this.copy(
					isDigitExists = false
				)
				PasswordValidationError.LATIN_CHAR -> this.copy(
					isLatinCharsExists = false
				)
			}

		fun empty() = PasswordValidationState(
			isPasswordLengthValid = false,
			isSpecialCharExists = false,
			isDigitExists = false,
			isLatinCharsExists = false
		)

		fun fullValidState() = PasswordValidationState(
			isPasswordLengthValid = true,
			isSpecialCharExists = true,
			isDigitExists = true,
			isLatinCharsExists = true
		)
	}

}