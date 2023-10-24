package com.archoid.launch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archoid.core_ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
	private val router: Router,
	private val screens: Screens
): ViewModel() {

	fun navigateToNext() {
		viewModelScope.launch {
			delay(DELAY_LAUNCH_SCREEN)
			router.replaceScreen(
				screens.auth()
			)
		}
	}

	private companion object {
		const val DELAY_LAUNCH_SCREEN = 1000L
	}

}