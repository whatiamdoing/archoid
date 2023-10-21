package com.archoid.core_ui

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FlowRouter(private val router: Router): Router() {

	fun finish() {
		router.exit()
	}

	fun startFlow(screen: FragmentScreen) {
		router.navigateTo(screen)
	}

}