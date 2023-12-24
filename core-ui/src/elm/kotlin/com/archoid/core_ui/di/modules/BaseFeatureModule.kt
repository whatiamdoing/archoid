package com.archoid.core_ui.di.modules

import androidx.lifecycle.ViewModelProvider
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.FeatureFactory
import dagger.Binds
import dagger.Module

@Module
interface BaseFeatureModule {

	@Binds
	@PerFeature
	fun featureFactory(factory: FeatureFactory): ViewModelProvider.Factory

}