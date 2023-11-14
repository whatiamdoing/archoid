package com.archoid.domain.repository

import com.archoid.domain.entity.ProfileEntity
import com.archoid.domain.entity.params.RegisterParamsEntity
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

	suspend fun register(params: RegisterParamsEntity)

	fun observeProfile(): Flow<ProfileEntity>

}