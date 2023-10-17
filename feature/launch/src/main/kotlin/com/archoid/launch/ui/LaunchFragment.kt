package com.archoid.launch.ui

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.launch.R
import com.archoid.launch.databinding.FragmentLaunchBinding

class LaunchFragment: Fragment(R.layout.fragment_launch) {

	private val viewBinding by viewBinding(FragmentLaunchBinding::bind)

}