package com.archoid.main.ui

import com.archoid.core_ui.viewmodel.BaseViewModel
import com.archoid.domain.repository.AccountRepository
import com.archoid.main.MainRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
	private val accountRepository: AccountRepository,
	private val router: MainRouter
): BaseViewModel() {

	fun logout() {
		io {
			kotlin.runCatching {
				accountRepository.logout()
			}.fold(
				onSuccess = {
					router.toAuth()
				},
				onFailure = { error ->
					error.message?.let(::showMessage)
				}
			)
		}
	}

}