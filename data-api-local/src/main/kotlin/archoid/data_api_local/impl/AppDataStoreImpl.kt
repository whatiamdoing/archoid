package archoid.data_api_local.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import archoid.data_api_local.AppDataStore
import archoid.data_api_local.model.ProfileModel
import archoid.data_api_local.parseFromJson
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AppDataStoreImpl @Inject constructor(
	private val gson: Gson,
	private val context: Context
): AppDataStore {

	private val Context.appDataStore by preferencesDataStore(name = DATASTORE_NAME)

	override suspend fun updateProfile(profile: ProfileModel) {
		context.appDataStore.edit { prefs ->
			prefs[keyProfile] = gson.toJson(profile)
		}
	}

	override fun observeProfile(): Flow<ProfileModel> =
		context.appDataStore.data
			.map { prefs ->
				prefs[keyProfile]?.parseFromJson<ProfileModel>(gson = gson)
			}
			.filterNotNull()

	override suspend fun getProfile(): ProfileModel? =
		context.appDataStore.data
			.map { prefs ->
				prefs[keyProfile]?.parseFromJson<ProfileModel>(gson = gson)
			}
			.firstOrNull()

	override suspend fun clearProfile() {
		context.appDataStore.edit { prefs ->
			prefs.remove(keyProfile)
		}
	}

	private companion object {
		const val DATASTORE_NAME = "archoid"

		val keyProfile = stringPreferencesKey(name = "profile")
	}

}