package com.archoid.data.repository

import archoid.data_api_local.ds.AccountLocalDataSource
import com.archoid.domain.repository.AccountRepository
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
	private val accountLocalDataSource: AccountLocalDataSource
): AccountRepository