package com.archoid.modules.base

open class Module(
	private val name: String
): CharSequence by ":${name}" {

	constructor(
		name: String,
		group: Group
	): this(
		name = "${group.name}:$name"
	)

	override fun toString(): String = ":$name"

}