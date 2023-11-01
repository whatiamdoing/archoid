package archoid.data_api_local.di

import com.archoid.global.di.scopes.AppScope
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
	modules = [
		LocalDataModule::class,
		LocalDataSourceModule::class
    ]
)
interface LocalDataComponent: LocalDataSourcesDependencies {

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance
			gson: Gson
		): LocalDataComponent
	}

	companion object {
		fun start(gson: Gson) = DaggerLocalDataComponent
			.factory()
			.create(
				gson = gson
			)
	}

}