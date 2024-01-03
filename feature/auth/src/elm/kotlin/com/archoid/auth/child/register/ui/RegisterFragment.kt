package com.archoid.auth.child.register.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.AuthRouter
import com.archoid.auth.R
import com.archoid.auth.child.register.PasswordValidationState
import com.archoid.auth.child.register.ui.RegisterFeature.*
import com.archoid.auth.child.register.di.DaggerRegisterComponent
import com.archoid.auth.child.register.di.RegisterComponent
import com.archoid.auth.databinding.FragmentRegisterBinding
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.ElmFragment
import com.archoid.core_ui.utils.delayedExecute
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class RegisterFragment: ElmFragment<State, News>(layoutRes = R.layout.fragment_register) {

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

	override val feature by viewModel<RegisterFeature>()

	private val viewBinding by viewBinding(FragmentRegisterBinding::bind)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setTextWatchers()
	}

	private fun setTextWatchers() {
		with(viewBinding) {
			etName.addTextChangedListener { value ->
				feature.accept(
					Msg.SetName(
						name = value.toString()
					)
				)
			}
			etEmail.addTextChangedListener { value ->
				feature.accept(
					Msg.SetEmail(
						email = value.toString()
					)
				)
			}
			etPassword.addTextChangedListener { value ->
				feature.accept(
					Msg.SetPassword(
						password = value.toString()
					)
				)
			}
			etPasswordConfirm.addTextChangedListener { value ->
				feature.accept(
					Msg.SetPasswordConfirm(
						password = value.toString()
					)
				)
			}
		}
	}

	override fun setOnClickListeners() {
		with(viewBinding) {
			btnRegister.setOnClickListener {
				feature.accept(Msg.Register)
			}
			toolbar.setNavigationOnClickListener {
				authRouter.back()
			}
		}
	}

	override fun renderState(state: State) {
		with(viewBinding) {
			btnRegister.isEnabled = state.isRegisterAvailable
			btnRegister.isLoading = state.isRegisterInProgress

			diffState(state) {
				ifChanged(State::passwordValidation) { passwordValidation ->
					setPasswordValidationState(state = passwordValidation)
				}
				ifChanged(State::isPasswordConfirmMatch) { match ->
					confirmPasValidIndicatorMatch.setValidationState(valid = match)
				}
			}
		}
	}

	private fun setPasswordValidationState(state: PasswordValidationState) {
		with(viewBinding) {
			pasValidIndicatorLength.setValidationState(valid = state.isPasswordLengthValid)
			pasValidIndicatorLatinChars.setValidationState(valid = state.isLatinCharsExists)
			pasValidIndicatorDigits.setValidationState(valid = state.isDigitExists)
			pasValidIndicatorSpecialChar.setValidationState(valid = state.isSpecialCharExists)
		}
	}

	override fun handleNews(news: News) {
		when(news) {
			News.Registered -> {
				lifecycleScope.launch {
					showSnackbar(
						text = getString(
							com.archoid.resources.R.string.registered_success
						)
					)
					delayedExecute {
						authRouter.toMain()
					}
				}
			}
			is News.ShowMessage -> showSnackbar(text = news.message)
		}
	}

}