package com.archoid.main

import com.archoid.core_ui.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainRouter @Inject constructor(
	private val router: Router,
	private val screens: Screens
) {

	fun toAuth() = router.replaceScreen(screen = screens.auth())

}