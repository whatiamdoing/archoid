package com.archoid.auth.login.di

import androidx.lifecycle.ViewModel
import com.archoid.auth.login.LoginViewModel
import com.archoid.core_ui.di.modules.BaseViewModelModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginModule: BaseViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(LoginViewModel::class)
	@PerFeature
	fun loginViewModel(factory: LoginViewModel): ViewModel

}