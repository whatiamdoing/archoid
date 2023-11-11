package com.archoid.auth.child.register

import androidx.lifecycle.viewModelScope
import com.archoid.auth.AuthConstants
import com.archoid.auth.child.register.PasswordValidationState.Companion.setValidationError
import com.archoid.auth.usecase.ValidateEmailUseCase
import com.archoid.core_ui.Constants
import com.archoid.core_ui.utils.onFailure
import com.archoid.core_ui.utils.validate
import com.archoid.core_ui.utils.validated
import com.archoid.core_ui.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
	private val validateEmailUseCase: ValidateEmailUseCase
): BaseViewModel() {

	private var name: String? = null

	private var email: String? = null
	private val isEmailValid = MutableStateFlow(false)

	private val _passwordFlow = MutableStateFlow<String?>(null)
	private val _passwordValidationStateFlow = MutableStateFlow(PasswordValidationState.empty())
	val passwordValidationState get() = _passwordValidationStateFlow.asStateFlow()

	private val _passwordConfirmFlow = MutableStateFlow<String?>(null)
	val isPasswordConfirmMatch = combine(_passwordFlow, _passwordConfirmFlow) { password, passwordConfirm ->
		!password.isNullOrBlank() && password == passwordConfirm
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.Lazily,
		initialValue = false
	)

	val isRegisterAvailable = combine(isEmailValid, passwordValidationState, isPasswordConfirmMatch) { isEmailValid, validationState, passwordConfirmMatch ->
		isEmailValid && validationState.isFullValid() && passwordConfirmMatch
	}

	fun setEmail(value: String) {
		this.email = value
		isEmailValid.update { validateEmailUseCase.invoke(email = value) }
	}

	fun setName(value: String) {
		this.name = value
	}

	fun setPassword(value: String) = _passwordFlow.update { value }

	fun setPasswordConfirm(value: String) = _passwordConfirmFlow.update { value }

	@OptIn(FlowPreview::class)
	private fun startPasswordValidator() {
		val latinCharConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_LETTER_CONDITION)
		val digitConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_DIGIT_CONDITION)
		val specialCharConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_SPECIAL_CHAR_CONDITION)

		_passwordFlow
			.debounce(Constants.Delays.HALF_OF_SECOND)
			.filterNotNull()
			.distinctUntilChanged()
			.onEach { password ->
				var validState = PasswordValidationState.fullValidState()

				validate(
					validated(
						value = password,
						validator = { it.length >= AuthConstants.MIN_PASSWORD_LENGTH },
						error = PasswordValidationError.SHORT
					),
					validated(
						value = password,
						validator = { it.matches(latinCharConditionRegex) },
						error = PasswordValidationError.LATIN_CHAR
					),
					validated(
						value = password,
						validator = { it.matches(digitConditionRegex) },
						error = PasswordValidationError.DIGIT
					),
					validated(
						value = password,
						validator = { it.matches(specialCharConditionRegex) },
						error = PasswordValidationError.SPECIAL_CHAR
					)
				).onFailure { errors ->
					errors.forEach { error ->
						validState = validState.setValidationError(error = error)
					}
				}

				_passwordValidationStateFlow.value = validState
			}
			.launchIn(viewModelScope)
	}

	init {
		startPasswordValidator()
	}

}