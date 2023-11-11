package com.archoid.auth

import com.archoid.auth.child.login.LoginScreen
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.FlowFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal class AuthFlowFragment: FlowFragment() {

	init {
		componentBuilder = {
			DaggerAuthComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	override val launchScreen: FragmentScreen = LoginScreen

	override fun initComponent() {
		getComponent<AuthComponent>().inject(this)
	}

}