package com.archoid.main.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.ElmFragment
import com.archoid.main.MainRouter
import com.archoid.main.R
import com.archoid.main.databinding.FragmentMainBinding
import com.archoid.main.di.DaggerMainComponent
import com.archoid.main.di.MainComponent
import com.archoid.main.ui.MainFeature.*
import javax.inject.Inject

class MainFragment: ElmFragment<State, News>(R.layout.fragment_main) {

	override fun initComponent() {
		componentBuilder = {
			DaggerMainComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}

		getComponent<MainComponent>().inject(this)
	}

	private val viewBinding by viewBinding(FragmentMainBinding::bind)

	@Inject
	lateinit var mainRouter: MainRouter

	override val feature: MainFeature by viewModel<MainFeature>()

	override fun setOnClickListeners() {
		with(viewBinding) {
			toolbar.setOnMenuItemClickListener { item ->
				when(item.itemId) {
					R.id.action_logout -> {
						feature.accept(Msg.Logout)
						true
					}
					else -> false
				}
			}
		}
	}

	override fun renderState(state: State) = Unit

	override fun handleNews(news: News) {
		when(news) {
			is News.LogoutFailure -> news.errorMessage?.let(::showSnackbar)
			News.ToAuth -> mainRouter.toAuth()
		}
	}

}