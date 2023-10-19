package com.archoid.modules

import com.archoid.modules.base.Group
import com.archoid.modules.base.Module

object Modules {

	object CoreUi : Module(name = "core-ui")

	object Global : Module(name = "global")

	object Data : Module(name = "data")

	object DataApiLocal : Module(name = "data-api-local")

	object DataApiRemote : Module(name = "data-api-remote")

	object Domain : Module(name = "domain")

	object Feature : Group(name = "feature") {

		object Launch : Module(
			group = this,
			name = "launch"
		)

	}

}