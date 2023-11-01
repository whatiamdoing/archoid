package com.archoid.data.di

import archoid.data_api_local.di.LocalDataComponent
import archoid.data_api_local.di.LocalDataSourcesDependencies
import com.archoid.global.di.scopes.AppScope
import com.google.gson.Gson
import dagger.Component

@AppScope
@Component(
	modules = [RepositoryModule::class],
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
			gson: Gson
		): RepositoryComponent {
			val localDataDependencies = LocalDataComponent.start(gson = gson)
			return DaggerRepositoryComponent
				.factory()
				.create(
					localDataDependencies = localDataDependencies
				)
		}
	}

}