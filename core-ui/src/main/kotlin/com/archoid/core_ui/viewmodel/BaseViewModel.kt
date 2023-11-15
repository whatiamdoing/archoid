package com.archoid.core_ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archoid.global.di.qualifier.DefaultDispatcher
import com.archoid.global.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

	@IoDispatcher
	@Inject
	protected lateinit var ioDispatcher: CoroutineDispatcher

	@DefaultDispatcher
	@Inject
	protected lateinit var defaultDispatcher: CoroutineDispatcher

	protected val _message = MutableSharedFlow<String>()
	val message get() = _message.asSharedFlow()

	protected fun showMessage(msg: String) {
		viewModelScope.launch {
			_message.emit(msg)
		}
	}

	protected fun io(
		start: CoroutineStart = CoroutineStart.DEFAULT,
		job: Job = viewModelScope.coroutineContext.job,
		block: suspend CoroutineScope.() -> Unit
 	) {
		viewModelScope.launch(
			context = job + ioDispatcher,
			start = start,
			block = block
		)
	}

	protected fun default(
		start: CoroutineStart = CoroutineStart.DEFAULT,
		job: Job = viewModelScope.coroutineContext.job,
		block: suspend CoroutineScope.() -> Unit
	) {
		viewModelScope.launch(
			context = job + defaultDispatcher,
			start = start,
			block = block
		)
	}

}