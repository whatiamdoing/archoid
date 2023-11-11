package com.archoid.auth

import com.archoid.auth.child.login.di.LoginDependencies
import com.archoid.auth.child.register.di.RegisterDependencies
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.ComponentDependencyKey
import com.archoid.core_ui.di.dependencies.FlowComponentDependencies
import com.archoid.core_ui.di.modules.FlowFragmentModule
import com.archoid.core_ui.di.utils.PerFlow
import com.archoid.domain.repository.AccountRepository
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

interface AuthDependencies : FlowComponentDependencies {
	fun accountRepository(): AccountRepository
}

@PerFlow
@Component(
	modules = [
		FlowFragmentModule::class,
		ChildDependenciesModule::class
	],
	dependencies = [AuthDependencies::class]
)
internal interface AuthComponent : DaggerComponent, AuthFlowDependencies {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: AuthDependencies
		): AuthComponent
	}

	fun inject(fragment: AuthFlowFragment)
}

internal interface AuthFlowDependencies:
	RegisterDependencies,
	LoginDependencies

@Module
internal interface ChildDependenciesModule {

	@PerFlow
	@Binds
	@IntoMap
	@ComponentDependencyKey(LoginDependencies::class)
	fun loginDependencies(component: AuthComponent): ComponentDependencies

	@PerFlow
	@Binds
	@IntoMap
	@ComponentDependencyKey(RegisterDependencies::class)
	fun registerDependencies(component: AuthComponent): ComponentDependencies

}