package com.archoid.core_ui.di.dependencies

import com.archoid.global.di.qualifier.DefaultDispatcher
import com.archoid.global.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersDependencies {
	@IoDispatcher
	fun ioDispatcher(): CoroutineDispatcher
	@DefaultDispatcher
	fun defaultDispatcher(): CoroutineDispatcher
}