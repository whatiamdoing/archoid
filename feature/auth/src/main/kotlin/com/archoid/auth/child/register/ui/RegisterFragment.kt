package com.archoid.auth.child.register.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.AuthRouter
import com.archoid.auth.R
import com.archoid.auth.child.register.RegisterViewModel
import com.archoid.auth.child.register.di.DaggerRegisterComponent
import com.archoid.auth.child.register.di.RegisterComponent
import com.archoid.auth.databinding.FragmentRegisterBinding
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.MvvmFragment
import javax.inject.Inject

class RegisterFragment: MvvmFragment<RegisterViewModel>(layoutRes = R.layout.fragment_register) {

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

	override val viewModel by viewModel<RegisterViewModel>()

	private val viewBinding by viewBinding(FragmentRegisterBinding::bind)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setTextWatchers()
		initObservers()
	}

	private fun initObservers() = with(viewModel) {
		with(viewBinding) {
			passwordValidationStateFlow.observe { state ->
				pasValidIndicatorLength.setValidationState(valid = state.isPasswordLengthValid)
				pasValidIndicatorLatinChars.setValidationState(valid = state.isLatinCharsExists)
				pasValidIndicatorDigits.setValidationState(valid = state.isDigitExists)
				pasValidIndicatorSpecialChar.setValidationState(valid = state.isSpecialCharExists)
			}
			isPasswordConfirmMatchFlow.observe(confirmPasValidIndicatorMatch::setValidationState)
			isRegisterAvailableFlow.observe(btnRegister::setEnabled)
			isRegisterInProgressFlow.observe(btnRegister::isLoading::set)
			newsFlow.observe(::processNews)
		}
	}

	private fun setTextWatchers() {
		with(viewBinding) {
			etName.addTextChangedListener { value ->
				viewModel.setName(value = value.toString())
			}
			etEmail.addTextChangedListener { value ->
				viewModel.setEmail(value = value.toString())
			}
			etPassword.addTextChangedListener { value ->
				viewModel.setPassword(value = value.toString())
			}
			etPasswordConfirm.addTextChangedListener { value ->
				viewModel.setPasswordConfirm(value = value.toString())
			}
		}
	}

	private fun processNews(news: RegisterViewModel.News) {
		when(news) {
			RegisterViewModel.News.OnRegistered -> {
				//TODO navigate to next screen
				showSnackbar(text = "Successfully registered")
			}
		}
	}

	override fun setOnClickListeners() {
		with(viewBinding) {
			btnRegister.setOnClickListener {
				viewModel.register()
			}
			toolbar.setNavigationOnClickListener {
				authRouter.back()
			}
		}
	}

}