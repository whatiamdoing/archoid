package com.archoid.auth.usecase

import com.archoid.auth.AuthConstants
import javax.inject.Inject

class ValidateAuthPasswordUseCase @Inject constructor(): (String) -> Boolean {

	private val passwordRegex by lazy {
		Regex(AuthConstants.RegexPatterns.PASSWORD)
	}

	override fun invoke(password: String): Boolean =
		passwordRegex.matches(password)

}