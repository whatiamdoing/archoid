package com.archoid.auth.login.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.AuthRouter
import com.archoid.auth.R
import com.archoid.auth.databinding.FragmentLoginBinding
import com.archoid.auth.login.LoginViewModel
import com.archoid.auth.login.di.DaggerLoginComponent
import com.archoid.auth.login.di.LoginComponent
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.MvvmFragment
import javax.inject.Inject

internal class LoginFragment: MvvmFragment<LoginViewModel>(layoutRes = R.layout.fragment_login) {

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

	@Inject
	override lateinit var viewModel: LoginViewModel

	private val viewBinding by viewBinding(FragmentLoginBinding::bind)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setTextWatchers()
		initObservers()
	}

	private fun initObservers() {
		viewModel.isLoginDataValidFlow.observe { isValid ->
			viewBinding.btnLogin.isEnabled = isValid
		}
	}

	private fun setTextWatchers() {
		with(viewBinding) {
			etEmail.addTextChangedListener { text ->
				viewModel.setEmail(value = text.toString())
			}
			etPassword.addTextChangedListener { text ->
				viewModel.setPassword(value = text.toString())
			}
		}
	}

	override fun setOnClickListeners() {
		with(viewBinding) {
			btnRegister.setOnClickListener {
				authRouter.toRegister()
			}
			btnLogin.setOnClickListener {
				//TODO handle on login click
				showSnackbar(text = "Login")
			}
		}
	}

}