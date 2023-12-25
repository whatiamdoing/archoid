package com.archoid.launch.ui

import com.archoid.core_ui.feature.ElmFeature
import com.archoid.core_ui.feature.SideEffect
import com.archoid.core_ui.feature.Update
import com.archoid.domain.repository.AccountRepository
import com.archoid.global.utils.extensions.orFalse
import com.archoid.launch.ui.LaunchFeature.*
import javax.inject.Inject

class LaunchFeature @Inject constructor(
	private val accountRepository: AccountRepository
): ElmFeature<State, Msg, Cmd, News>(
	initialState = State,
	reducer = { msg, _ ->
		when(msg) {
			Msg.CheckAuthorizationAndNavigateToNext -> {
				Update(
					cmd = Cmd.CheckAuthorizationAndNavigateToNext
				)
			}
		}
	},
	commandHandler = { cmd ->
		when(cmd) {
			Cmd.CheckAuthorizationAndNavigateToNext -> {
				val isAuthed = runCatching {
					val profile = accountRepository.getProfileLocal()
					profile != null
				}.getOrNull().orFalse()

				SideEffect(
					news = if (isAuthed) {
						News.OnAuth
					} else {
						News.ToAuth
					}
				)
			}
		}
	}
) {

	data object State

	sealed interface Msg {
		data object CheckAuthorizationAndNavigateToNext: Msg
	}

	sealed interface Cmd {
		data object CheckAuthorizationAndNavigateToNext: Cmd
	}

	sealed interface News {
		data object OnAuth: News
		data object ToAuth: News
	}

}