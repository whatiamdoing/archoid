package com.archoid.launch.di

import androidx.lifecycle.ViewModel
import com.archoid.core_ui.di.modules.BaseFeatureModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.FeatureKey
import com.archoid.launch.ui.LaunchFeature
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LaunchModule: BaseFeatureModule {

	@Binds
	@IntoMap
	@FeatureKey(LaunchFeature::class)
	@PerFeature
	fun launchFeature(feature: LaunchFeature): ViewModel

}