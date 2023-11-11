package com.archoid.auth

object AuthConstants {

	const val MIN_PASSWORD_LENGTH = 8

	object RegexPatterns {
		const val PASSWORD = """(^(?=.{8,}$))[a-zA-Z0-9]*[!$#%]+[a-zA-Z0-9]*$"""
		const val EMAIL = """[a-zA-Z0-9_-]+@[a-zA-Z]+\.[a-z]+"""
		const val PASSWORD_LETTER_CONDITION = ".*[a-zA-Z].*"
		const val PASSWORD_DIGIT_CONDITION = ".*[0-9].*"
		const val PASSWORD_SPECIAL_CHAR_CONDITION = ".*[!$#%].*"
	}

}