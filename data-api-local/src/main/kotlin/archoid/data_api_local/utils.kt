package archoid.data_api_local

import com.google.gson.Gson

inline fun <reified T> String.parseFromJson(gson: Gson): T =
	gson.fromJson(this, T::class.java)