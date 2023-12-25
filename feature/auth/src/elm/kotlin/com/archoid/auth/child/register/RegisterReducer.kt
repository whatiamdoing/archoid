package com.archoid.auth.child.register

import com.archoid.auth.child.register.ui.RegisterFeature.*
import com.archoid.auth.usecase.ValidateEmailUseCase
import com.archoid.core_ui.feature.Reducer
import com.archoid.core_ui.feature.Update

internal class RegisterReducer(
	private val validateEmailUseCase: ValidateEmailUseCase
): Reducer<Msg, State, Cmd> {

	override suspend fun invoke(msg: Msg, state: State): Update<State, Cmd> =
		when(msg) {
			is Msg.SetEmail -> {
				val isEmailValid = validateEmailUseCase.invoke(email = msg.email)
				Update(
					state = state.copy(
						email = msg.email,
						isEmailValid = isEmailValid,
						isRegisterAvailable = isRegisterAvailableFlow(
							isEmailValid = isEmailValid,
							isPasswordConfirmMatch = state.isPasswordConfirmMatch,
							passwordValidation = state.passwordValidation
						)
					)
				)
			}
			is Msg.SetPassword -> {
				val isPasswordConfirmMatch = isPasswordConfirmMatch(
					password = msg.password,
					passwordConfirm = state.passwordConfirm
				)
				Update(
					state = state.copy(
						password = msg.password,
						isPasswordConfirmMatch = isPasswordConfirmMatch,
						isRegisterAvailable = isRegisterAvailableFlow(
							isEmailValid = state.isEmailValid,
							isPasswordConfirmMatch = isPasswordConfirmMatch,
							passwordValidation = state.passwordValidation
						)
					),
					cmd = Cmd.ValidatePassword(
						password = msg.password
					)
				)
			}
			is Msg.SetPasswordConfirm -> {
				val isPasswordConfirmMatch = isPasswordConfirmMatch(
					password = state.password,
					passwordConfirm = msg.password
				)
				Update(
					state = state.copy(
						passwordConfirm = msg.password,
						isPasswordConfirmMatch = isPasswordConfirmMatch,
						isRegisterAvailable = isRegisterAvailableFlow(
							isEmailValid = state.isEmailValid,
							isPasswordConfirmMatch = isPasswordConfirmMatch,
							passwordValidation = state.passwordValidation
						)
					)
				)
			}
			Msg.Register -> {
				Update(
					state = state.copy(
						isRegisterInProgress = true
					),
					cmd = Cmd.Register(
						name = state.name,
						email = state.email,
						password = state.password
					)
				)
			}
			Msg.StopRegistering -> {
				Update(
					state = state.copy(
						isRegisterInProgress = false
					)
				)
			}
			is Msg.SetPasswordValidation -> {
				Update(
					state = state.copy(
						passwordValidation = msg.validation,
						isRegisterAvailable = isRegisterAvailableFlow(
							isEmailValid = state.isEmailValid,
							isPasswordConfirmMatch = state.isPasswordConfirmMatch,
							passwordValidation = msg.validation
						)
					)
				)
			}
			is Msg.SetName -> {
				Update(
					state = state.copy(
						name = msg.name
					)
				)
			}
		}

	private fun isPasswordConfirmMatch(
		password: String,
		passwordConfirm: String
	) = password.isNotBlank() && password == passwordConfirm

	private fun isRegisterAvailableFlow(
		isEmailValid: Boolean,
		isPasswordConfirmMatch: Boolean,
		passwordValidation: PasswordValidationState
	) = isEmailValid && passwordValidation.isFullValid() && isPasswordConfirmMatch

}