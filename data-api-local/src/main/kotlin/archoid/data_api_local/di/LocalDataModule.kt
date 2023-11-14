package archoid.data_api_local.di

import archoid.data_api_local.AppDataStore
import archoid.data_api_local.impl.AppDataStoreImpl
import com.archoid.global.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
internal interface LocalDataModule {

	@Binds
	@AppScope
	fun appDataStoreImpl(impl: AppDataStoreImpl): AppDataStore

}