package com.archoid.core_ui.di.dependencies

import com.archoid.core_ui.Screens
import com.github.terrakok.cicerone.Router

interface FlowComponentDependencies: ComponentDependencies, DispatchersDependencies {
	fun router(): Router
	fun screens(): Screens
}