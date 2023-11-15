package com.archoid.auth

import com.archoid.auth.child.register.ui.RegisterScreen
import com.archoid.core_ui.FlowRouter
import com.archoid.core_ui.Screens
import javax.inject.Inject

internal class AuthRouter @Inject constructor(
	private val flowRouter: FlowRouter,
	private val screens: Screens
) {

	fun toRegister() = flowRouter.navigateTo(screen = RegisterScreen)

	fun toMain() = flowRouter.replaceRoot(screen = screens.main())

	fun back() = flowRouter.exit()

}