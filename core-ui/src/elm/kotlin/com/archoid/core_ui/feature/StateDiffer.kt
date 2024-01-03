package com.archoid.core_ui.feature

import androidx.annotation.MainThread

class StateDiffer<State>(
	private val getCurrentViewState: () -> State?
) {

	private var newViewState: State? = null

	@MainThread
	fun with(
		state: State,
		block: StateDiffer<State>.() -> Unit
	) {
		newViewState = state
		block.invoke(this)
	}

	@MainThread
	fun <Field> ifChanged(
		accessor: (State) -> Field,
		areSame: (Field, Field) -> Boolean = { current, new ->
			current == new
		},
		block: (Field) -> Unit
	) {
		val currentViewState = getCurrentViewState.invoke()
		val newField = accessor.invoke(newViewState ?: throwRenderError())

		if (currentViewState == null) {
			block.invoke(newField)
			return
		}

		val currentField = accessor.invoke(currentViewState)

		if (!areSame.invoke(currentField, newField)) {
			block.invoke(newField)
		}
	}

	private fun throwRenderError(): Nothing =
		error("StateDiffer Error: 'newViewState' is null. Ensure 'with' is invoked with a valid state.")

}