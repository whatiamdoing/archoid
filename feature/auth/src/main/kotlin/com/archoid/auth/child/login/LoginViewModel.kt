package com.archoid.auth.child.login

import androidx.lifecycle.viewModelScope
import com.archoid.auth.usecase.ValidateAuthPasswordUseCase
import com.archoid.auth.usecase.ValidateEmailUseCase
import com.archoid.core_ui.Constants
import com.archoid.core_ui.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
	private val validateEmailUseCase: ValidateEmailUseCase,
	private val validateAuthPasswordUseCase: ValidateAuthPasswordUseCase
): BaseViewModel() {

	private val emailFlow = MutableStateFlow<String?>(null)
	private val passwordFlow = MutableStateFlow<String?>(null)

	private val _isLoginDataValidFlow = MutableStateFlow(false)
	val isLoginDataValidFlow get() = _isLoginDataValidFlow.asStateFlow()

	private val _isLoginInProgress = MutableStateFlow(false)
	val isLoginInProgressFlow get() = _isLoginInProgress.asStateFlow()

	fun setEmail(value: String) = emailFlow.update { value }

	fun setPassword(value: String) = passwordFlow.update { value }

	fun login() {
		viewModelScope.launch {
			_isLoginInProgress.value = true
			delay(1000)
			_isLoginInProgress.value = false
		}
	}

	@OptIn(FlowPreview::class)
	private fun setLoginDataValidator() {
		val preparedEmailFlow = emailFlow
			.filterNotNull()
			.distinctUntilChanged()
			.onEach {
				_isLoginDataValidFlow.value = false
			}
			.debounce(Constants.Delays.TEXT_FIELD_CHANGE)

		val preparedPasswordFlow = passwordFlow
			.filterNotNull()
			.distinctUntilChanged()
			.onEach {
				_isLoginDataValidFlow.value = false
			}
			.debounce(Constants.Delays.TEXT_FIELD_CHANGE)

		combine(preparedEmailFlow, preparedPasswordFlow) { email, password ->
			val isPasswordValid = validateAuthPasswordUseCase.invoke(password)
			val isEmailValid = validateEmailUseCase.invoke(email)
			isPasswordValid && isEmailValid
		}.onEach {  valid ->
			_isLoginDataValidFlow.value = valid
		}.launchIn(
			scope = viewModelScope
		)
	}

	init {
		setLoginDataValidator()
	}

}