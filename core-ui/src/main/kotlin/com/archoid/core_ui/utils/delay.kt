package com.archoid.core_ui.utils

import com.archoid.core_ui.Constants
import kotlinx.coroutines.delay

suspend fun delayedExecute(
	delayValue: Long = Constants.Delays.HALF_OF_SECOND,
	block: suspend () -> Unit
) {
	delay(delayValue)
	block.invoke()
}