package com.archoid.main.ui

import com.archoid.core_ui.feature.ElmFeature
import com.archoid.core_ui.feature.SideEffect
import com.archoid.core_ui.feature.Update
import com.archoid.domain.repository.AccountRepository
import com.archoid.main.ui.MainFeature.Cmd
import com.archoid.main.ui.MainFeature.Msg
import com.archoid.main.ui.MainFeature.News
import com.archoid.main.ui.MainFeature.State
import javax.inject.Inject

class MainFeature @Inject constructor(
	private val accountRepository: AccountRepository,
): ElmFeature<State, Msg, Cmd, News>(
	initialState = State,
	reducer = { msg, _ ->
		when(msg) {
			Msg.Logout -> Update(
				cmd = Cmd.Logout
			)
		}
	},
	commandHandler = { cmd ->
		when(cmd) {
			Cmd.Logout -> {
				runCatching {
					accountRepository.logout()
				}.fold(
					onSuccess = {
						SideEffect(news = News.ToAuth)
					},
					onFailure = { error ->
						SideEffect(news = News.LogoutFailure(error.message))
					}
				)
			}
		}
	}
) {

	data object State

	sealed interface Msg {
		data object Logout: Msg
	}

	sealed interface Cmd {
		data object Logout: Cmd
	}

	sealed interface News {
		data object ToAuth: News
		data class LogoutFailure(val errorMessage: String?): News
	}

}