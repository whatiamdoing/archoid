package com.archoid.navigation.di.module

import com.archoid.navigation.Screens
import com.archoid.navigation.di.NavigationScope
import com.archoid.navigation.impl.ScreensImpl
import dagger.Binds
import dagger.Module

@Module
internal interface NavigationBindsModule {

	@NavigationScope
	@Binds
	fun screens(impl: ScreensImpl): Screens

}