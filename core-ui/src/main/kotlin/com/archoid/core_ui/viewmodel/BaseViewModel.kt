package com.archoid.core_ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

	protected val _message = MutableSharedFlow<String>()
	val message get() = _message.asSharedFlow()

	protected fun showMessage(msg: String) {
		viewModelScope.launch {
			_message.emit(msg)
		}
	}

}