package com.archoid.data.mapper

import archoid.data_api_local.model.ProfileModel
import com.archoid.domain.entity.ProfileEntity
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

	operator fun invoke(profile: ProfileModel): ProfileEntity =
		ProfileEntity(
			id = profile.id,
			name = profile.name,
			email = profile.email
		)

}