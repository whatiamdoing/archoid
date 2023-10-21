package com.archoid.auth.login.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.AuthRouter
import com.archoid.auth.R
import com.archoid.auth.databinding.FragmentLoginBinding
import com.archoid.auth.login.di.DaggerLoginComponent
import com.archoid.auth.login.di.LoginComponent
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.BaseFragment
import javax.inject.Inject

internal class LoginFragment: BaseFragment(layoutRes = R.layout.fragment_login) {

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

	private val viewBinding by viewBinding(FragmentLoginBinding::bind)

	override fun setOnClickListeners() {
		with(viewBinding) {
			btnRegister.setOnClickListener {
				authRouter.toRegister()
			}
		}
	}

}