package com.archoid.auth.child.register.di

import androidx.lifecycle.ViewModel
import com.archoid.auth.child.register.ui.RegisterFeature
import com.archoid.core_ui.di.modules.BaseFeatureModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.FeatureKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface RegisterModule: BaseFeatureModule {

	@PerFeature
	@Binds
	@IntoMap
	@FeatureKey(RegisterFeature::class)
	fun registerViewModel(viewModel: RegisterFeature): ViewModel

}