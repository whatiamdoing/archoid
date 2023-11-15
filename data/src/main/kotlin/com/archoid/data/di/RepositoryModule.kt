package com.archoid.data.di

import com.archoid.data.repository.AccountRepositoryImpl
import com.archoid.domain.repository.AccountRepository
import com.archoid.global.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
internal interface RepositoryModule {

	@AppScope
	@Binds
	fun accountRepository(impl: AccountRepositoryImpl): AccountRepository

}