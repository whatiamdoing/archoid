package com.archoid.core_ui.utils

sealed interface Validated<out T, out E> {
	data class Valid<T>(val value: T): Validated<T, Nothing>
	data class Invalid<E>(val error: E): Validated<Nothing, E>
}

fun <T, E> validated(
	value: T,
	validator: (T) -> Boolean,
	error: E
): Validated<T, E> = if (validator.invoke(value)) {
	Validated.Valid(value = value)
} else {
	Validated.Invalid(error = error)
}

fun <E> validate(
	vararg validations: Validated<*, E>
): Validated<List<*>, List<E>> {
	val invalid = validations.filterIsInstance<Validated.Invalid<E>>()

	if (invalid.isNotEmpty()) {
		return Validated.Invalid(error = invalid.map(Validated.Invalid<E>::error))
	}

	val valid = validations.filterIsInstance<Validated.Valid<*>>()

	return Validated.Valid(value = valid.map(Validated.Valid<*>::value))
}

inline fun <T, E> Validated<T, E>.fold(
	onSuccess: (T) -> Unit,
	onError: (E) -> Unit
) {
	when(this) {
		is Validated.Valid -> onSuccess.invoke(this.value)
		is Validated.Invalid -> onError.invoke(this.error)
	}
}

inline fun <E> Validated<*, E>.onFailure(
	block: (E) -> Unit
) {
	if (this is Validated.Invalid) {
		block.invoke(this.error)
	}
}

inline fun <T> Validated<T, *>.onSuccess(
	block: (T) -> Unit
) {
	if (this is Validated.Valid) {
		block.invoke(this.value)
	}
}