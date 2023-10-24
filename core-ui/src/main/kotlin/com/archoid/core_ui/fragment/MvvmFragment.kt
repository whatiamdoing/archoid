package com.archoid.core_ui.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class MvvmFragment(
	@LayoutRes val layoutRes: Int
) : BaseFragment(layoutRes = layoutRes) {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	inline fun <reified VM : ViewModel> Fragment.viewModel() =
		viewModels<VM> { viewModelFactory }

}