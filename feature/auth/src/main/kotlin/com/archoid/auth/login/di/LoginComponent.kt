package com.archoid.auth.login.di

import com.archoid.auth.AuthRouter
import com.archoid.auth.login.ui.LoginFragment
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.domain.repository.AccountRepository
import dagger.Component

internal interface LoginDependencies: ComponentDependencies {
	fun authRouter(): AuthRouter
	fun accountRepository(): AccountRepository
}

@PerFeature
@Component(
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