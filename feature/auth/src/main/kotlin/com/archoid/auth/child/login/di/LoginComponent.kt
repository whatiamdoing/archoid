package com.archoid.auth.child.login.di

import com.archoid.auth.AuthRouter
import com.archoid.auth.child.login.ui.LoginFragment
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.DispatchersDependencies
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.core_ui.tools.ResourceManager
import com.archoid.domain.repository.AccountRepository
import dagger.Component

internal interface LoginDependencies: ComponentDependencies, DispatchersDependencies {
	fun authRouter(): AuthRouter
	fun accountRepository(): AccountRepository
	fun resourceManager(): ResourceManager
}

@PerFeature
@Component(
	modules = [LoginModule::class],
	dependencies = [LoginDependencies::class]
)
internal interface LoginComponent: DaggerComponent {

	@Component.Factory
	interface Factory {
		fun create(
			dependencies: LoginDependencies
		): LoginComponent
	}

	fun inject(fragment: LoginFragment)
}