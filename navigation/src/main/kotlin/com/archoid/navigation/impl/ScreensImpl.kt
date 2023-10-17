package com.archoid.navigation.impl

import com.archoid.launch.LaunchScreen
import com.archoid.navigation.Screens
import javax.inject.Inject

internal class ScreensImpl @Inject constructor(): Screens {

	override fun launch() = LaunchScreen

}