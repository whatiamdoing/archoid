package com.archoid.auth.register.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.AuthRouter
import com.archoid.auth.R
import com.archoid.auth.databinding.FragmentRegisterBinding
import com.archoid.auth.register.di.DaggerRegisterComponent
import com.archoid.auth.register.di.RegisterComponent
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.BaseFragment
import javax.inject.Inject

class RegisterFragment: BaseFragment(layoutRes = R.layout.fragment_register) {

	init {
		componentBuilder = {
			DaggerRegisterComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	override fun initComponent() {
		getComponent<RegisterComponent>().inject(this)
	}

	@Inject
	internal lateinit var authRouter: AuthRouter

	private val viewBinding by viewBinding(FragmentRegisterBinding::bind)

	override fun setOnClickListeners() {
		viewBinding.toolbar.setNavigationOnClickListener {
			authRouter.back()
		}
	}

}