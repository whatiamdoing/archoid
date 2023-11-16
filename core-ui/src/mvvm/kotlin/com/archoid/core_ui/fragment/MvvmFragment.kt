package com.archoid.core_ui.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.archoid.core_ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


abstract class MvvmFragment<ViewModel: BaseViewModel>(
	@LayoutRes val layoutRes: Int
) : BaseFragment(layoutRes = layoutRes) {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	abstract val viewModel: ViewModel

	protected inline fun <reified VM : ViewModel> Fragment.viewModel() =
		viewModels<VM> { viewModelFactory }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initBaseObservers()
	}

	private fun initBaseObservers() {
		viewModel.message.observe(::showSnackbar)
	}

	protected fun <T> Flow<T>.observe(action: suspend (T) -> Unit) =
		this.flowWithLifecycle(
			 lifecycle = lifecycle,
			 minActiveState = Lifecycle.State.STARTED
		)
			.onEach(action)
		    .launchIn(lifecycleScope)

}