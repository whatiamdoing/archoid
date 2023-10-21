package com.archoid.auth.register.di

import com.archoid.auth.AuthRouter
import com.archoid.auth.register.ui.RegisterFragment
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.utils.PerFeature
import dagger.Component

internal interface RegisterDependencies: ComponentDependencies {
	fun authRouter(): AuthRouter
}

@PerFeature
@Component(
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