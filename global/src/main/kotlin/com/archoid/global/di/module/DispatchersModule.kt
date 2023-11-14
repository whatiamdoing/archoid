package com.archoid.global.di.module

import com.archoid.global.di.qualifier.DefaultDispatcher
import com.archoid.global.di.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatchersModule {

	@DefaultDispatcher
	@Provides
	fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

	@IoDispatcher
	@Provides
	fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

}