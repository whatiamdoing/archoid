package com.archoid.data.di

import android.app.Application
import archoid.data_api_local.di.LocalDataComponent
import archoid.data_api_local.di.LocalDataSourcesDependencies
import com.archoid.global.di.module.DispatchersModule
import com.archoid.global.di.scope.AppScope
import com.google.gson.Gson
import dagger.Component

@AppScope
@Component(
	modules = [
		RepositoryModule::class,
		DispatchersModule::class
    ],
	dependencies = [LocalDataSourcesDependencies::class]
)
interface RepositoryComponent: RepositoriesDependencies {

	@Component.Factory
	interface Factory {
		fun create(
			localDataDependencies: LocalDataSourcesDependencies
		): RepositoryComponent
	}

	companion object {
		fun start(
			gson: Gson,
			context: Application
		): RepositoryComponent {
			val localDataDependencies = LocalDataComponent.start(
				gson = gson,
				context = context
			)
			return DaggerRepositoryComponent
				.factory()
				.create(
					localDataDependencies = localDataDependencies
				)
		}
	}

}