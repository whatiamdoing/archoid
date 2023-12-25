package com.archoid.auth.child.login.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.AuthRouter
import com.archoid.auth.R
import com.archoid.auth.child.login.di.DaggerLoginComponent
import com.archoid.auth.child.login.di.LoginComponent
import com.archoid.auth.child.login.ui.LoginFeature.*
import com.archoid.auth.databinding.FragmentLoginBinding
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.ElmFragment
import com.archoid.core_ui.utils.delayedExecute
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class LoginFragment: ElmFragment<State, News>(layoutRes = R.layout.fragment_login) {

	init {
		componentBuilder = {
			DaggerLoginComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	override fun initComponent() {
		getComponent<LoginComponent>().inject(this)
	}

	@Inject
	internal lateinit var authRouter: AuthRouter

	override val feature by viewModel<LoginFeature>()

	private val viewBinding by viewBinding(FragmentLoginBinding::bind)

	override fun renderState(state: State) {
		with(viewBinding) {
			btnLogin.isEnabled = state.isLoginDataValid
			btnLogin.isLoading = state.isLoginInProgress
		}
	}

	override fun handleNews(news: News) {
		when(news) {
			News.LoggedIn -> lifecycleScope.launch {
				showSnackbar(
					text = getString(
						com.archoid.resources.R.string.auth_success
					)
				)
				delayedExecute {
					authRouter.toMain()
				}
			}
			is News.LoginFailure -> news.message?.let(::showSnackbar)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setTextWatchers()
	}

	private fun setTextWatchers() {
		with(viewBinding) {
			etEmail.addTextChangedListener { text ->
				feature.accept(Msg.SetEmail(email = text.toString()))
			}
			etPassword.addTextChangedListener { text ->
				feature.accept(Msg.SetPassword(password = text.toString()))
			}
		}
	}

	override fun setOnClickListeners() {
		with(viewBinding) {
			btnLogin.setOnClickListener {
				feature.accept(Msg.Login)
			}
			btnToRegister.setOnClickListener {
				authRouter.toRegister()
			}
		}
	}

}