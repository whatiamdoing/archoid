package com.archoid.app.navigation

import com.archoid.auth.AuthFlowScreen
import com.archoid.core_ui.Screens
import com.archoid.launch.LaunchScreen
import com.archoid.main.MainScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

internal class ScreensImpl @Inject constructor(): Screens {

	override fun launch() = LaunchScreen

	override fun auth() = AuthFlowScreen

	override fun main(): FragmentScreen = MainScreen

}