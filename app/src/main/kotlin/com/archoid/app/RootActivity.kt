package com.archoid.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.app.databinding.ActivityRootBinding
import com.archoid.core_ui.Screens
import com.archoid.core_ui.di.dependencies.ChildComponentDependenciesHolder
import com.archoid.core_ui.di.dependencies.HasChildDependencies
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class RootActivity : AppCompatActivity(), HasChildDependencies {

	init {
		App.appComponent.inject(this)
	}

	@Inject
	lateinit var navigatorHolder: NavigatorHolder

	@Inject
	lateinit var screens: Screens

	@Inject
	override lateinit var dependencies: ChildComponentDependenciesHolder

	private val navigator = AppNavigator(this, R.id.container)

	private val viewBinding by viewBinding(ActivityRootBinding::bind)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_root)

		if (savedInstanceState == null) {
			navigator.applyCommands(
				arrayOf(
					Replace(
						screen = screens.launch()
					)
				)
			)
		}
	}

	override fun onResume() {
		super.onResume()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}

}