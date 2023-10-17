package com.archoid.navigation.di

import com.archoid.navigation.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface NavigationDependencies {
	fun screens(): Screens
	fun router(): Router
	fun navigatorHolder(): NavigatorHolder
}