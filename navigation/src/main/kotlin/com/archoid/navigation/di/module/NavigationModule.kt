package com.archoid.navigation.di.module

import com.archoid.navigation.di.NavigationScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
internal class NavigationModule {

	@NavigationScope
	@Provides
	fun cicerone() = Cicerone.create()

	@NavigationScope
	@Provides
	fun router(cicerone: Cicerone<Router>) = cicerone.router

	@NavigationScope
	@Provides
	fun navigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()

}