package com.archoid.core_ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.archoid.core_ui.FlowRouter
import com.archoid.core_ui.R
import com.archoid.core_ui.di.dependencies.ChildComponentDependenciesHolder
import com.archoid.core_ui.di.dependencies.HasChildDependencies
import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

abstract class FlowFragment: BaseFragment(R.layout.fragment_flow), HasChildDependencies {

	@Inject
	override lateinit var dependencies: ChildComponentDependenciesHolder

	@Inject
	protected lateinit var flowRouter: FlowRouter

	@Inject
	protected lateinit var router: Router

	@Inject
	protected lateinit var navigatorHolder: NavigatorHolder

	private lateinit var navigator: FlowNavigator

	abstract val launchScreen: FragmentScreen

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		navigator = FlowNavigator()

		if (childFragmentManager.fragments.isEmpty()) {
			navigator.applyCommands(
				arrayOf(
					BackTo(null),
					Replace(launchScreen)
				)
			)
		}
	}

	override fun onPause() {
		navigatorHolder.removeNavigator()
		super.onPause()
	}

	override fun onResume() {
		super.onResume()
		navigatorHolder.setNavigator(navigator)
	}

	private inner class FlowNavigator: AppNavigator(
		activity = requireActivity(),
		fragmentManager = childFragmentManager,
		containerId = R.id.flow_container
	) {
		override fun backToUnexisting(screen: Screen) {
			flowRouter.navigateTo(screen)
		}

		override fun activityBack() {
			router.exit()
		}

		override fun setupFragmentTransaction(
			screen: FragmentScreen,
			fragmentTransaction: FragmentTransaction,
			currentFragment: Fragment?,
			nextFragment: Fragment
		) {
			if (nextFragment.tag != currentFragment?.tag) {
				fragmentTransaction.setCustomAnimations(
					R.anim.slide_in_right,
					R.anim.slide_out_left,
					R.anim.slide_in_left,
					R.anim.slide_out_right
				)
			}

			fragmentTransaction.setReorderingAllowed(true)
		}
	}

}