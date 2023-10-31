package com.archoid.data.di

import com.archoid.domain.repository.AccountRepository

interface RepositoriesDependencies {
	fun accountRepository(): AccountRepository
}