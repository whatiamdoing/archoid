package com.archoid.auth.child.login.ui

import com.archoid.auth.child.login.ui.LoginFeature.Cmd
import com.archoid.auth.child.login.ui.LoginFeature.Msg
import com.archoid.auth.child.login.ui.LoginFeature.News
import com.archoid.auth.child.login.ui.LoginFeature.State
import com.archoid.auth.usecase.ValidateAuthPasswordUseCase
import com.archoid.auth.usecase.ValidateEmailUseCase
import com.archoid.core_ui.Constants
import com.archoid.core_ui.feature.ElmFeature
import com.archoid.core_ui.feature.SideEffect
import com.archoid.core_ui.feature.Update
import com.archoid.domain.entity.params.LoginParamsEntity
import com.archoid.domain.repository.AccountRepository
import javax.inject.Inject

internal class LoginFeature @Inject constructor(
	private val validateEmailUseCase: ValidateEmailUseCase,
	private val validateAuthPasswordUseCase: ValidateAuthPasswordUseCase,
	private val accountRepository: AccountRepository
) : ElmFeature<State, Msg, Cmd, News>(
	initialState = State(),
	reducer = { msg, state ->
		when(msg) {
			is Msg.SetEmail -> {
				Update(
					state = state.copy(
						email = msg.email,
						isLoginDataValid = false
					),
					cmd = Cmd.ValidateData(
						email = msg.email,
						password = state.password
					)
				)
			}
			is Msg.SetPassword -> {
				Update(
					state = state.copy(
						password = msg.password,
						isLoginDataValid = false
					),
					cmd = Cmd.ValidateData(
						email = state.email,
						password = msg.password
					)
				)
			}
			Msg.Login -> {
				Update(
					state = state.copy(
						isLoginInProgress = true
					),
					cmd = Cmd.Login(
						email = state.email,
						password = state.password
					)
				)
			}
			Msg.StopLoginLoading -> {
				Update(
					state = state.copy(
						isLoginInProgress = false
					)
				)
			}
			is Msg.SetLoginDataValidation -> {
				Update(
					state = state.copy(
						isLoginDataValid = msg.valid
					)
				)
			}
		}
	},
	commandHandler = { cmd ->
		when(cmd) {
			is Cmd.Login -> {
				runCatching {
					accountRepository.login(
						params = LoginParamsEntity(
							email = cmd.email,
							password = cmd.password
						)
					)
				}.fold(
					onSuccess = {
						SideEffect(msg = Msg.StopLoginLoading, news = News.LoggedIn)
					},
					onFailure = { error ->
						SideEffect(msg = Msg.StopLoginLoading, news = News.LoginFailure(error.message))
					}
				)
			}
			is Cmd.ValidateData -> {
				val isEmailValid = validateEmailUseCase.invoke(email = cmd.email)
				val isPasswordValid = validateAuthPasswordUseCase.invoke(password = cmd.password)
				val isLoginDataValid = isEmailValid && isPasswordValid

				SideEffect(
					msg = Msg.SetLoginDataValidation(
						valid = isLoginDataValid
					)
				)
			}
		}
	}
) {

	data class State(
		val email: String = Constants.Others.EMPTY_STR,
		val password: String = Constants.Others.EMPTY_STR,
		val isLoginInProgress: Boolean = false,
		val isLoginDataValid: Boolean = false
	)

	sealed interface Msg {
		data class SetEmail(val email: String): Msg
		data class SetPassword(val password: String): Msg
		data object Login: Msg
		data object StopLoginLoading: Msg
		data class SetLoginDataValidation(val valid: Boolean): Msg
	}

	sealed interface Cmd {
		data class Login(
			val email: String,
			val password: String
		): Cmd
		data class ValidateData(
			val email: String,
			val password: String
		): Cmd
	}

	sealed interface News {
		data object LoggedIn: News
		data class LoginFailure(val message: String?): News
	}

}