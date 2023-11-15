package com.archoid.data.repository

import archoid.data_api_local.ds.AccountLocalDataSource
import archoid.data_api_local.model.ProfileModel
import com.archoid.data.mapper.ProfileMapper
import com.archoid.domain.entity.ProfileEntity
import com.archoid.domain.entity.params.RegisterParamsEntity
import com.archoid.domain.repository.AccountRepository
import com.archoid.global.di.qualifier.DefaultDispatcher
import com.archoid.global.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
	@IoDispatcher
	private val ioDispatcher: CoroutineDispatcher,
	@DefaultDispatcher
	private val defaultDispatcher:  CoroutineDispatcher,
	private val accountLocalDataSource: AccountLocalDataSource,
	private val profileMapper: ProfileMapper
): AccountRepository {

	override suspend fun register(params: RegisterParamsEntity) {
		delay(1500)
		//TODO remote data source
		val profile = ProfileModel(
			id = Int.MAX_VALUE,
			name = params.name,
			email = params.email
		)
		accountLocalDataSource.updateProfile(profile = profile)
	}

	override fun observeProfile(): Flow<ProfileEntity> =
		accountLocalDataSource.observeProfile()
			.map(profileMapper::invoke)

}