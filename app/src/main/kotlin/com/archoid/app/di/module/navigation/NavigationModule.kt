package com.archoid.app.di.module.navigation

import com.archoid.app.di.AppScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
internal class NavigationModule {

	@AppScope
	@Provides
	fun cicerone() = Cicerone.create()

	@AppScope
	@Provides
	fun navigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()

	@AppScope
	@Provides
	fun router(cicerone: Cicerone<Router>) = cicerone.router

}