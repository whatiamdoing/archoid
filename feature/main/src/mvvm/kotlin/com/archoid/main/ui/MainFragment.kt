package com.archoid.main.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.MvvmFragment
import com.archoid.main.R
import com.archoid.main.databinding.FragmentMainBinding
import com.archoid.main.di.DaggerMainComponent
import com.archoid.main.di.MainComponent

class MainFragment: MvvmFragment<MainViewModel>(R.layout.fragment_main) {

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

	override val viewModel: MainViewModel by viewModel()

	override fun setOnClickListeners() {
		with(viewBinding) {
			toolbar.setOnMenuItemClickListener { item ->
				when(item.itemId) {
					R.id.action_logout -> {
						viewModel.logout()
						true
					}
					else -> false
				}
			}
		}
	}

}