package com.whatiamdoing.core_ui

import com.archoid.core_ui.utils.Validated
import com.archoid.core_ui.utils.fold
import com.archoid.core_ui.utils.onFailure
import com.archoid.core_ui.utils.onSuccess
import com.archoid.core_ui.utils.validate
import com.archoid.core_ui.utils.validated
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ValidationTest {

	@Test
	fun `invoke onSuccess WHEN 3 different validations are Valid EXPECTED valid values size 3`() {
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

	@Test
	fun `invoke onFailure WHEN 2 of 3 validations are Invalid EXPECTED errors size 2`() {
		val blankErrorMessage = "Blank"
		val blankValidator: (String) -> Boolean = String::isNotBlank
		validate(
			validated(
				value = "",
				validator = blankValidator,
				error = blankErrorMessage
			),
			validated(
				value = "123",
				validator = blankValidator,
				error = blankErrorMessage
			),
			validated(
				value = " ",
				validator = blankValidator,
				error = blankErrorMessage
			)
		).onFailure { errors ->
			assertEquals(2, errors.size)
		}
	}

	@Test
	fun `invoke onSuccess WHEN 3 validations are Valid EXPECTED valid values size 3`() {
		val blankErrorMessage = "Blank"
		val blankValidator: (String) -> Boolean = String::isNotBlank
		validate(
			validated(
				value = "1",
				validator = blankValidator,
				error = blankErrorMessage
			),
			validated(
				value = "2",
				validator = blankValidator,
				error = blankErrorMessage
			),
			validated(
				value = "3",
				validator = blankValidator,
				error = blankErrorMessage
			)
		).onSuccess { values ->
			assertEquals(3, values.size)
		}
	}

	@Test
	fun `invoke fold WHEN validated is Valid EXPECTED wasOnErrorCalled=false and wasOnSuccessCalled=true`() {
		val validValue = 10
		val validated = Validated.Valid(validValue)

		var wasSuccessCalled = false
		var wasErrorCalled = false

		validated.fold(
			onSuccess = { value ->
				assertEquals(validValue, value)
				wasSuccessCalled = true
			},
			onError = { _ ->
				wasErrorCalled = true
			}
		)

		assertTrue(wasSuccessCalled)
		assertFalse(wasErrorCalled)
	}

	@Test
	fun `invoke fold WHEN validated is Invalid EXPECTED wasOnErrorCalled=true and wasOnSuccessCalled=false`() {
		val errorMessage = "Error"
		val validated = Validated.Invalid(errorMessage)

		var wasOnSuccessCalled = false
		var wasOnErrorCalled = false

		validated.fold(
			onSuccess = { _ ->
				wasOnSuccessCalled = true
			},
			onError = { error ->
				assertEquals(errorMessage, error)
				wasOnErrorCalled = true
			}
		)

		assertTrue(wasOnErrorCalled)
		assertFalse(wasOnSuccessCalled)
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