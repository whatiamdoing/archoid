package com.archoid.data.di

import com.archoid.global.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(
	modules = [RepositoryModule::class]
)
interface RepositoryComponent: RepositoriesDependencies