package com.archoid.main.di

import androidx.lifecycle.ViewModel
import com.archoid.core_ui.di.modules.BaseViewModelModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.ViewModelKey
import com.archoid.main.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MainModule: BaseViewModelModule {

	@PerFeature
	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel::class)
	fun mainViewModel(viewModel: MainViewModel): ViewModel

}