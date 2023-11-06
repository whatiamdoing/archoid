package archoid.data_api_local.ds.impl

import archoid.data_api_local.AppDataStore
import archoid.data_api_local.ds.AccountLocalDataSource
import javax.inject.Inject

internal class AccountLocalDataSourceImpl @Inject constructor(
	private val appDataStore: AppDataStore
): AccountLocalDataSource