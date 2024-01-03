package com.archoid.core_ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.archoid.core_ui.feature.ElmFeature
import com.archoid.core_ui.feature.StateDiffer
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class ElmFragment<State, News>(
	@LayoutRes private val layoutRes: Int
): BaseFragment(layoutRes = layoutRes) {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private var currentViewState: State? = null

	private val stateDiffer = StateDiffer(
		getCurrentViewState = ::currentViewState
	)

	abstract val feature: ElmFeature<State, *, *, News>

	protected inline fun <reified VM : ViewModel> Fragment.viewModel() =
		viewModels<VM> { viewModelFactory }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		feature.stateFlow
			.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
			.onEach { state ->
				renderState(state = state)
				currentViewState = state
			}
			.launchIn(lifecycleScope)

		feature.newsFlow
			.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
			.onEach(::handleNews)
			.launchIn(lifecycleScope)
	}

	abstract fun renderState(state: State)

	abstract fun handleNews(news: News)

	protected fun diffState(
		state: State,
		block: StateDiffer<State>.() -> Unit
	) = stateDiffer.with(
		state = state,
		block = block
	)

	init {
		lifecycleScope.launch {
			repeatOnLifecycle(
				state = Lifecycle.State.STARTED
			) {
				currentViewState = null
			}
		}
	}

}