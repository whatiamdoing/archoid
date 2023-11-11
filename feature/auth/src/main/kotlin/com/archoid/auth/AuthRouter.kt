package com.archoid.auth

import com.archoid.auth.child.register.ui.RegisterScreen
import com.archoid.core_ui.FlowRouter
import javax.inject.Inject

internal class AuthRouter @Inject constructor(
	private val flowRouter: FlowRouter
) {

	fun toRegister() = flowRouter.navigateTo(RegisterScreen)

	fun back() = flowRouter.exit()

}