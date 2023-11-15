package com.archoid.domain.repository

import com.archoid.domain.entity.ProfileEntity
import com.archoid.domain.entity.params.LoginParamsEntity
import com.archoid.domain.entity.params.RegisterParamsEntity
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

	suspend fun register(params: RegisterParamsEntity)

	suspend fun login(params: LoginParamsEntity)

	fun observeProfile(): Flow<ProfileEntity>

	suspend fun getProfileLocal(): ProfileEntity?

	suspend fun logout()

}