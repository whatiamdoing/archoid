package com.archoid.auth.login

import androidx.lifecycle.viewModelScope
import com.archoid.core_ui.Constants
import com.archoid.core_ui.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LoginViewModel @Inject constructor(): BaseViewModel() {

	private val emailFlow = MutableStateFlow<String?>(null)
	private val passwordFlow = MutableStateFlow<String?>(null)

	private val passwordRegex by lazy {
		Regex(Constants.Regex.PASSWORD)
	}
	private val emailRegex by lazy {
		Regex(Constants.Regex.EMAIL)
	}

	private val _isLoginDataValidFlow = MutableStateFlow(false)
	val isLoginDataValidFlow get() = _isLoginDataValidFlow.asStateFlow()

	fun setEmail(value: String) = emailFlow.update { value }

	fun setPassword(value: String) = passwordFlow.update { value }

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
			val isPasswordValid = passwordRegex.matches(password)
			val isEmailValid = emailRegex.matches(email)
			isPasswordValid && isEmailValid
		}.onEach {  valid ->
			_isLoginDataValidFlow.value = valid
		}.launchIn(viewModelScope)
	}

	init {
		setLoginDataValidator()
	}

}