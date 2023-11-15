package archoid.data_api_local.ds.impl

import archoid.data_api_local.AppDataStore
import archoid.data_api_local.ds.AccountLocalDataSource
import archoid.data_api_local.model.ProfileModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AccountLocalDataSourceImpl @Inject constructor(
	private val appDataStore: AppDataStore
): AccountLocalDataSource {

	override suspend fun updateProfile(profile: ProfileModel) =
		appDataStore.updateProfile(profile)

	override fun observeProfile(): Flow<ProfileModel> =
		appDataStore.observeProfile()

	override suspend fun getProfile(): ProfileModel? =
		appDataStore.getProfile()

}