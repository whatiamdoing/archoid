package com.archoid.launch.di

import androidx.lifecycle.ViewModel
import com.archoid.core_ui.di.modules.BaseViewModelModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.ViewModelKey
import com.archoid.launch.ui.LaunchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LaunchModule: BaseViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(LaunchViewModel::class)
	@PerFeature
	fun launchViewModel(viewModel: LaunchViewModel): ViewModel

}