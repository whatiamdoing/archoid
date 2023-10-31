package com.archoid.core_ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel: ViewModel() {

	protected val _message = MutableSharedFlow<String>()
	val message get() = _message.asSharedFlow()

}