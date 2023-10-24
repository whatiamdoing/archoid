package com.archoid.app.di.module.navigation

import com.archoid.app.navigation.ScreensImpl
import com.archoid.core_ui.Screens
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface NavigationBindsModule {

	@Reusable
	@Binds
	fun screens(impl: ScreensImpl): Screens

}