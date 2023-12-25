package com.archoid.main.di

import androidx.lifecycle.ViewModel
import com.archoid.core_ui.di.modules.BaseFeatureModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.FeatureKey
import com.archoid.main.ui.MainFeature
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MainModule: BaseFeatureModule {

	@PerFeature
	@Binds
	@IntoMap
	@FeatureKey(MainFeature::class)
	fun mainFeature(feature: MainFeature): ViewModel

}