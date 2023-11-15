package com.archoid.launch.ui

import androidx.lifecycle.viewModelScope
import com.archoid.core_ui.Screens
import com.archoid.core_ui.viewmodel.BaseViewModel
import com.archoid.domain.repository.AccountRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
	private val router: Router,
	private val screens: Screens,
	private val accountRepository: AccountRepository
): BaseViewModel() {

	fun navigateToNext() {
		viewModelScope.launch {
			val profile = accountRepository.getProfileLocal()
			val isAuthed = profile != null

			delay(DELAY_LAUNCH_SCREEN)

			ensureActive()

			router.replaceScreen(
				if (isAuthed) {
					screens.main()
				} else {
					screens.auth()
				}
			)
		}
	}

	private companion object {
		const val DELAY_LAUNCH_SCREEN = 1000L
	}

}