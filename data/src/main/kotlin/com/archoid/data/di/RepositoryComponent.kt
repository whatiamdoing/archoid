package com.archoid.data.di

import dagger.Component

@Component(
	modules = [RepositoryModule::class]
)
interface RepositoryComponent: RepositoriesDependencies