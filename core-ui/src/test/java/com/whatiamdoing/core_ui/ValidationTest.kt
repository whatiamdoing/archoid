package com.whatiamdoing.core_ui

import com.archoid.core_ui.utils.onSuccess
import com.archoid.core_ui.utils.validate
import com.archoid.core_ui.utils.validated
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ValidationTest {

	@Test
	fun validate_isCorrect() {
		val regex = Regex(testRegexPattern)
		validate(
			validated(
				value = testStr,
				validator = { str -> str.length >= minStrLength },
				error = TestValidationError.SHORT_STR
			),
			validated(
				value = a + b,
				validator = { sum -> sum == abSum },
				TestValidationError.INCORRECT_SUM
			),
			validated(
				value = testRegexStr,
				validator = { str -> regex.matches(str) },
				error = TestValidationError.REGEX_NO_MATCH
			)
		).onSuccess { data ->
			assertEquals(3, data.size)
		}
	}

	private companion object {
		const val minStrLength = 10
		const val testStr = "Some test data"

		const val a = 32
		const val b = 64
		const val abSum = 96

		const val testRegexStr = "gooooogle"
		const val testRegexPattern = "go+gle"
	}

	private enum class TestValidationError {
		SHORT_STR,
		INCORRECT_SUM,
		REGEX_NO_MATCH
	}

}