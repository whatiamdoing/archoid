package com.archoid.app.di

import android.app.Application
import com.archoid.app.RootActivity
import com.archoid.app.di.module.dependencies.FlowsDependencies
import com.archoid.app.di.module.dependencies.FlowsDependenciesModule
import com.archoid.app.di.module.navigation.NavigationBindsModule
import com.archoid.app.di.module.navigation.NavigationModule
import com.archoid.data.di.RepositoriesDependencies
import com.archoid.data.di.RepositoryComponent
import com.archoid.global.di.module.DispatchersModule
import com.archoid.global.di.scope.AppScope
import com.google.gson.Gson
import dagger.Component

@AppScope
@Component(
	modules = [
		NavigationModule::class,
		NavigationBindsModule::class,
		FlowsDependenciesModule::class,
		DispatchersModule::class
	],
	dependencies = [
		RepositoriesDependencies::class
	]
)
interface AppComponent :
	FlowsDependencies,
	RepositoriesDependencies
{
	fun inject(activity: RootActivity)

	@Component.Factory
	interface Factory {
		fun create(
			repositoriesDependencies: RepositoriesDependencies
		): AppComponent
	}

	companion object {
		fun start(appContext: Application): AppComponent {
			val gson = Gson()
			val repositoriesDependencies = RepositoryComponent.start(
				gson = gson,
				context = appContext
			)
			return DaggerAppComponent
				.factory()
				.create(
					repositoriesDependencies = repositoriesDependencies
				)
		}
	}
}