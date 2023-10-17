package com.archoid.navigation.di

import com.archoid.navigation.di.module.NavigationBindsModule
import com.archoid.navigation.di.module.NavigationModule
import dagger.Component

@NavigationScope
@Component(
	modules = [
		NavigationModule::class,
		NavigationBindsModule::class
	]
)
interface NavigationComponent: NavigationDependencies