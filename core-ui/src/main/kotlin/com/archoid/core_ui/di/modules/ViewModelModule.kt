package com.archoid.core_ui.di.modules

import androidx.lifecycle.ViewModelProvider
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.ViewModelsFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

	@Binds
	@PerFeature
	fun viewModelsFactory(factory: ViewModelsFactory): ViewModelProvider.Factory

}