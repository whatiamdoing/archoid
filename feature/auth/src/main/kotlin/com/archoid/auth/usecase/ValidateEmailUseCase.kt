package com.archoid.auth.usecase

import com.archoid.auth.AuthConstants
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(): (String) -> Boolean {

	private val emailRegex by lazy {
		Regex(AuthConstants.RegexPatterns.EMAIL)
	}

	override fun invoke(email: String): Boolean =
		emailRegex.matches(email)

}