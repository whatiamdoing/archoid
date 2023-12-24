package com.archoid.launch.ui

import android.os.Bundle
import com.archoid.core_ui.Screens
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.ElmFragment
import com.archoid.launch.di.LaunchComponent
import com.archoid.launch.R
import com.archoid.launch.di.DaggerLaunchComponent
import com.archoid.launch.ui.LaunchFeature.*
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LaunchFragment : ElmFragment<State, News>(R.layout.fragment_launch) {

	init {
		componentBuilder = {
			DaggerLaunchComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	override fun initComponent() {
		getComponent<LaunchComponent>().inject(this)
	}

	@Inject
	lateinit var screens: Screens

	@Inject
	lateinit var router: Router

	override val feature by viewModel<LaunchFeature>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		feature.accept(Msg.CheckAuthorizationAndNavigateToNext)
	}

	override fun renderState(state: State) = Unit

	override fun handleNews(news: News) {
		when(news) {
			News.OnAuth -> {
				router.replaceScreen(
					screen = screens.main()
				)
			}
			News.ToAuth -> {
				router.replaceScreen(
					screen = screens.auth()
				)
			}
		}
	}

}