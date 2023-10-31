package com.archoid.data.di

import com.archoid.data.repository.AccountRepositoryImpl
import com.archoid.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {

	@Singleton
	@Binds
	fun accountRepository(impl: AccountRepositoryImpl): AccountRepository

}