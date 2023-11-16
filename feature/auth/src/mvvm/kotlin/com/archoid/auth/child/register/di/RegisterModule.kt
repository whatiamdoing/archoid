package com.archoid.auth.child.register.di

import androidx.lifecycle.ViewModel
import com.archoid.auth.child.register.RegisterViewModel
import com.archoid.core_ui.di.modules.BaseViewModelModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface RegisterModule: BaseViewModelModule {

	@PerFeature
	@Binds
	@IntoMap
	@ViewModelKey(RegisterViewModel::class)
	fun registerViewModel(viewModel: RegisterViewModel): ViewModel

}