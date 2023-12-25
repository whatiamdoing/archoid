package com.archoid.auth.child.register.ui

import com.archoid.auth.child.register.PasswordValidationState
import com.archoid.auth.child.register.RegisterCommandHandler
import com.archoid.auth.child.register.RegisterReducer
import com.archoid.auth.child.register.ui.RegisterFeature.Cmd
import com.archoid.auth.child.register.ui.RegisterFeature.Msg
import com.archoid.auth.child.register.ui.RegisterFeature.News
import com.archoid.auth.child.register.ui.RegisterFeature.State
import com.archoid.auth.usecase.ValidateEmailUseCase
import com.archoid.core_ui.Constants
import com.archoid.core_ui.feature.ElmFeature
import com.archoid.domain.repository.AccountRepository
import javax.inject.Inject

internal class RegisterFeature @Inject constructor(
	validateEmailUseCase: ValidateEmailUseCase,
	accountRepository: AccountRepository
) : ElmFeature<State, Msg, Cmd, News>(
	initialState = State(),
	reducer = RegisterReducer(
		validateEmailUseCase = validateEmailUseCase
	),
	commandHandler = RegisterCommandHandler(
		accountRepository = accountRepository
	)
) {

	data class State(
		val name: String = Constants.Others.EMPTY_STR,
		val email: String = Constants.Others.EMPTY_STR,
		val password: String = Constants.Others.EMPTY_STR,
		val passwordConfirm: String = Constants.Others.EMPTY_STR,
		val passwordValidation: PasswordValidationState = PasswordValidationState.empty(),
		val isEmailValid: Boolean = false,
		val isPasswordConfirmMatch: Boolean = false,
		val isRegisterAvailable: Boolean = false,
		val isRegisterInProgress: Boolean = false
	)

	sealed interface Msg {
		data class SetName(val name: String): Msg
		data class SetEmail(val email: String): Msg
		data class SetPassword(val password: String): Msg
		data class SetPasswordConfirm(val password: String): Msg
		data object Register: Msg
		data object StopRegistering: Msg
		data class SetPasswordValidation(val validation: PasswordValidationState): Msg
	}

	sealed interface Cmd {
		data class Register(
			val name: String,
			val email: String,
			val password: String
		): Cmd

		data class ValidatePassword(val password: String): Cmd
	}

	sealed interface News {
		data object Registered : News
		data class ShowMessage(val message: String): News
	}

}