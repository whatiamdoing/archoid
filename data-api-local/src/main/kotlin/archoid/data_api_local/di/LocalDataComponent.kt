package archoid.data_api_local.di

import android.content.Context
import com.archoid.global.di.scope.AppScope
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
			gson: Gson,
			@BindsInstance
			context: Context
		): LocalDataComponent
	}

	companion object {
		fun start(
			gson: Gson,
			context: Context
		) = DaggerLocalDataComponent
			.factory()
			.create(
				gson = gson,
				context = context
			)
	}

}