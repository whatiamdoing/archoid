package com.archoid.auth.child.login.di

import androidx.lifecycle.ViewModel
import com.archoid.auth.child.login.ui.LoginFeature
import com.archoid.core_ui.di.modules.BaseFeatureModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.FeatureKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface LoginModule: BaseFeatureModule {

	@Binds
	@IntoMap
	@FeatureKey(LoginFeature::class)
	@PerFeature
	fun loginFeature(factory: LoginFeature): ViewModel

}