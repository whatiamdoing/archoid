package com.archoid.app.di.navigation.module

import com.archoid.app.di.AppScope
import com.archoid.app.navigation.ScreensImpl
import com.archoid.core_ui.Screens
import dagger.Binds
import dagger.Module

@Module
internal interface NavigationBindsModule {

	@AppScope
	@Binds
	fun screens(impl: ScreensImpl): Screens

}