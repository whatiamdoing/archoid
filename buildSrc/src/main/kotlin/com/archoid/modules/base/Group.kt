package com.archoid.modules.base

open class Group {

	val name: String

	constructor(name: String) {
		this.name = name
	}

	constructor(
		name: String,
		group: Group
	): this(
		name = "${group.name}:$name"
	)

}