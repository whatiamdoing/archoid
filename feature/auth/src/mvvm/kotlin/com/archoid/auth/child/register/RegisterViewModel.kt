package com.archoid.auth.child.register

import androidx.lifecycle.viewModelScope
import com.archoid.auth.AuthConstants
import com.archoid.auth.AuthRouter
import com.archoid.auth.child.register.PasswordValidationState.Companion.setValidationError
import com.archoid.auth.usecase.ValidateEmailUseCase
import com.archoid.core_ui.Constants
import com.archoid.core_ui.tools.ResourceManager
import com.archoid.core_ui.utils.delayedExecute
import com.archoid.core_ui.utils.onFailure
import com.archoid.core_ui.utils.validate
import com.archoid.core_ui.utils.validated
import com.archoid.core_ui.viewmodel.BaseViewModel
import com.archoid.domain.entity.params.RegisterParamsEntity
import com.archoid.domain.repository.AccountRepository
import com.archoid.resources.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class RegisterViewModel @Inject constructor(
	private val authRouter: AuthRouter,
	private val resourceManager: ResourceManager,
	private val validateEmailUseCase: ValidateEmailUseCase,
	private val accountRepository: AccountRepository
) : BaseViewModel() {

	private var name: String? = null

	private var email: String? = null
	private val isEmailValid = MutableStateFlow(false)

	private val _passwordFlow = MutableStateFlow<String?>(null)
	private val _passwordValidationStateFlow = MutableStateFlow(PasswordValidationState.empty())
	val passwordValidationStateFlow get() = _passwordValidationStateFlow.asStateFlow()

	private val _passwordConfirmFlow = MutableStateFlow<String?>(null)
	val isPasswordConfirmMatchFlow =
		combine(_passwordFlow, _passwordConfirmFlow) { password, passwordConfirm ->
			!password.isNullOrBlank() && password == passwordConfirm
		}.stateIn(
			scope = viewModelScope,
			started = SharingStarted.Lazily,
			initialValue = false
		)

	val isRegisterAvailableFlow = combine(
		isEmailValid,
		passwordValidationStateFlow,
		isPasswordConfirmMatchFlow
	) { isEmailValid, validationState, passwordConfirmMatch ->
		isEmailValid && validationState.isFullValid() && passwordConfirmMatch
	}

	private val _isRegisterInProgressFlow = MutableStateFlow(false)
	val isRegisterInProgressFlow get() = _isRegisterInProgressFlow.asStateFlow()

	fun setEmail(value: String) {
		this.email = value
		isEmailValid.update { validateEmailUseCase.invoke(email = value) }
	}

	fun setName(value: String) {
		this.name = value
	}

	fun setPassword(value: String) = _passwordFlow.update { value }

	fun setPasswordConfirm(value: String) = _passwordConfirmFlow.update { value }

	fun register() {
		io {
			_isRegisterInProgressFlow.value = true

			kotlin.runCatching {
				val password = requireNotNull(_passwordFlow.value)
				accountRepository.register(
					params = RegisterParamsEntity(
						name = name,
						email = requireNotNull(email),
						password = password
					)
				)
			}.fold(
				onSuccess = {
					showMessage(
						msg = resourceManager.getString(
							R.string.auth_success
						)
					)
					delayedExecute {
						authRouter.toMain()
					}
				},
				onFailure = { error ->
					error.message?.let(::showMessage)
				}
			)

			_isRegisterInProgressFlow.value = false
		}
	}

	@OptIn(FlowPreview::class)
	private fun startPasswordValidator() {
		val latinCharConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_LETTER_CONDITION)
		val digitConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_DIGIT_CONDITION)
		val specialCharConditionRegex =
			Regex(AuthConstants.RegexPatterns.PASSWORD_SPECIAL_CHAR_CONDITION)

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

	sealed interface News {
		data object OnRegistered : News
	}

	init {
		startPasswordValidator()
	}

}