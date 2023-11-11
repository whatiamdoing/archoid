package com.archoid.auth.child.register.di

import androidx.lifecycle.ViewModel
import com.archoid.auth.AuthRouter
import com.archoid.auth.child.register.RegisterViewModel
import com.archoid.auth.child.register.ui.RegisterFragment
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.modules.BaseViewModelModule
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.viewmodel.utils.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

internal interface RegisterDependencies: ComponentDependencies {
	fun authRouter(): AuthRouter
}

@PerFeature
@Component(
	modules = [RegisterModule::class],
	dependencies = [RegisterDependencies::class]
)
internal interface RegisterComponent: DaggerComponent {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: RegisterDependencies
		): RegisterComponent
	}

	fun inject(fragment: RegisterFragment)
}

@Module
interface RegisterModule: BaseViewModelModule {

	@PerFeature
	@Binds
	@IntoMap
	@ViewModelKey(RegisterViewModel::class)
	fun registerViewModel(viewModel: RegisterViewModel): ViewModel

}