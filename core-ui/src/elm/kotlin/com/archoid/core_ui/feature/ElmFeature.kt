package com.archoid.core_ui.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archoid.global.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class Update<State, Cmd>(
	val state: State? = null,
	val cmd: Cmd? = null
) {
	constructor(state: State): this(
		state = state,
		cmd = null
	)
}

data class SideEffect<Msg, News>(
	val msg: Msg? = null,
	val news: News? = null
) {
	constructor(msg: Msg): this(
		msg = msg,
		news = null
	)
}

typealias Reducer<Msg, State, Cmd> = suspend (Msg, State) -> Update<State, Cmd>

typealias CommandHandler<Msg, Cmd, News> = suspend (Cmd) -> SideEffect<Msg, News>

abstract class ElmFeature<State, Msg, Cmd, News>(
	initialState: State,
	private val reducer: Reducer<Msg, State, Cmd>,
	private val commandHandler: CommandHandler<Msg, Cmd, News>
): ViewModel() {

	@Inject
	@IoDispatcher
	lateinit var ioDispatcher: CoroutineDispatcher

	private val _stateFlow = MutableStateFlow(initialState)
	val stateFlow get() = _stateFlow.asStateFlow()

	private val _newsFlow = MutableSharedFlow<News>()
	val newsFlow get() = _newsFlow.asSharedFlow()

	val state: State
		get() = _stateFlow.value

	private suspend fun handleMsg(msg: Msg) {
		val (state, cmd) = reducer.invoke(msg, _stateFlow.value)

		if (state != null) {
			_stateFlow.update { state }
		}

		if (cmd != null) {
			processCommand(cmd = cmd)
		}
	}

	private suspend fun processCommand(cmd: Cmd) {
		withContext(ioDispatcher) {
			val (msg, news) = commandHandler.invoke(cmd)

			if (msg != null) {
				handleMsg(msg)
			}

			if (news != null) {
				_newsFlow.emit(news)
			}
		}
	}

	fun accept(msg: Msg) {
		viewModelScope.launch {
			handleMsg(msg = msg)
		}
	}

}