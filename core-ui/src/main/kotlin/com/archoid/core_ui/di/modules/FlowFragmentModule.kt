package com.archoid.core_ui.di.modules

import com.archoid.core_ui.FlowRouter
import com.archoid.core_ui.di.utils.PerFlow
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
object FlowFragmentModule {

	@Provides
	@PerFlow
	fun cicerone(router: Router): Cicerone<FlowRouter> {
		val flowRouter = FlowRouter(router = router)
		return Cicerone.create(flowRouter)
	}

	@Provides
	@PerFlow
	fun flowRouter(cicerone: Cicerone<FlowRouter>): FlowRouter = cicerone.router

	@Provides
	@PerFlow
	fun flowNavigatorHolder(cicerone: Cicerone<FlowRouter>): NavigatorHolder = cicerone.getNavigatorHolder()

}