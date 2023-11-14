package archoid.data_api_local

import archoid.data_api_local.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface AppDataStore {

	suspend fun updateProfile(profile: ProfileModel)

	fun observeProfile(): Flow<ProfileModel>

	suspend fun getProfile(): ProfileModel?

}