package com.archoid.auth.child.register.di

import com.archoid.auth.AuthRouter
import com.archoid.auth.child.register.ui.RegisterFragment
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.DispatchersDependencies
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.tools.ResourceManager
import com.archoid.domain.repository.AccountRepository
import dagger.Component

internal interface RegisterDependencies: ComponentDependencies, DispatchersDependencies {
	fun authRouter(): AuthRouter
	fun accountRepository(): AccountRepository
	fun resourceManager(): ResourceManager
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