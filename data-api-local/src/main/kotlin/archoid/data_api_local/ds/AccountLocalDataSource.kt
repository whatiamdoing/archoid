package archoid.data_api_local.ds

import archoid.data_api_local.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface AccountLocalDataSource {

	suspend fun updateProfile(profile: ProfileModel)

	fun observeProfile(): Flow<ProfileModel>

	suspend fun getProfile(): ProfileModel?

	suspend fun clearProfile()

}