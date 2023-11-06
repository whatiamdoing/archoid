package archoid.data_api_local.di

import archoid.data_api_local.ds.AccountLocalDataSource
import archoid.data_api_local.ds.impl.AccountLocalDataSourceImpl
import com.archoid.global.di.scopes.AppScope
import dagger.Binds
import dagger.Module

interface LocalDataSourcesDependencies {
	fun accountDataSource(): AccountLocalDataSource
}

@Module
internal interface LocalDataSourceModule {

	@Binds
	@AppScope
	fun accountLocalDataSource(impl: AccountLocalDataSourceImpl): AccountLocalDataSource

}