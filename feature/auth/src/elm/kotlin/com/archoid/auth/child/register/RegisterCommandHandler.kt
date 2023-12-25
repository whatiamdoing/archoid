package com.archoid.auth.child.register

import com.archoid.auth.AuthConstants
import com.archoid.auth.child.register.PasswordValidationState.Companion.setValidationError
import com.archoid.auth.child.register.ui.RegisterFeature.*
import com.archoid.core_ui.feature.CommandHandler
import com.archoid.core_ui.feature.SideEffect
import com.archoid.core_ui.utils.onFailure
import com.archoid.core_ui.utils.validate
import com.archoid.core_ui.utils.validated
import com.archoid.domain.entity.params.RegisterParamsEntity
import com.archoid.domain.repository.AccountRepository

internal class RegisterCommandHandler(
	private val accountRepository: AccountRepository
): CommandHandler<Msg, Cmd, News> {

	private val latinCharConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_LETTER_CONDITION)
	private val digitConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_DIGIT_CONDITION)
	private val specialCharConditionRegex = Regex(AuthConstants.RegexPatterns.PASSWORD_SPECIAL_CHAR_CONDITION)

	override suspend fun invoke(cmd: Cmd): SideEffect<Msg, News> =
		when(cmd) {
			is Cmd.Register -> {
				runCatching {
					accountRepository.register(
						params = RegisterParamsEntity(
							name = cmd.name,
							email = cmd.email,
							password = cmd.password
						)
					)
				}.fold(
					onSuccess = {
						SideEffect(msg = Msg.StopRegistering, news = News.Registered)
					},
					onFailure = { error ->
						SideEffect(msg = Msg.StopRegistering, news = error.message?.let(News::ShowMessage))
					}
				)
			}
			is Cmd.ValidatePassword -> {
				var validationState = PasswordValidationState.fullValidState()

				validate(
					validated(
						value = cmd.password,
						validator = { it.length >= AuthConstants.MIN_PASSWORD_LENGTH },
						error = PasswordValidationError.SHORT
					),
					validated(
						value = cmd.password,
						validator = { it.matches(latinCharConditionRegex) },
						error = PasswordValidationError.LATIN_CHAR
					),
					validated(
						value = cmd.password,
						validator = { it.matches(digitConditionRegex) },
						error = PasswordValidationError.DIGIT
					),
					validated(
						value = cmd.password,
						validator = { it.matches(specialCharConditionRegex) },
						error = PasswordValidationError.SPECIAL_CHAR
					)
				).onFailure { errors ->
					errors.forEach { error ->
						validationState = validationState.setValidationError(error = error)
					}
				}

				SideEffect(msg = Msg.SetPasswordValidation(validation = validationState))
			}
		}

}