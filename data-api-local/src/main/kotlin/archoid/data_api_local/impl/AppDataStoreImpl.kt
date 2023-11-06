package archoid.data_api_local.impl

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import archoid.data_api_local.AppDataStore
import com.google.gson.Gson
import javax.inject.Inject

internal class AppDataStoreImpl @Inject constructor(
	private val gson: Gson
): AppDataStore {

	private val Context.appDataStore by preferencesDataStore(name = DATASTORE_NAME)

	private companion object {
		const val DATASTORE_NAME = "archoid"
	}

}