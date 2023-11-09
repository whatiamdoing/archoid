package com.archoid.core_ui.utils

sealed interface Validated<T, E> {
	data class Valid<T, E>(val value: T): Validated<T, E>
	data class Invalid<T, E>(val error: E): Validated<T, E>
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

fun <T, E> validate(
	vararg validations: Validated<T, E>
): Validated<List<T>, List<E>> {
	val invalid = validations.filterIsInstance<Validated.Invalid<T, E>>()

	if (invalid.isNotEmpty()) {
		return Validated.Invalid(error = invalid.map(Validated.Invalid<T, E>::error))
	}

	val valid = validations.filterIsInstance<Validated.Valid<T, E>>()

	return Validated.Valid(value = valid.map(Validated.Valid<T, E>::value))
}

inline fun <R, E> Validated<R, E>.fold(
	onSuccess: (R) -> Unit,
	onError: (E) -> Unit
) {
	when(this) {
		is Validated.Valid -> onSuccess.invoke(this.value)
		is Validated.Invalid -> onError.invoke(this.error)
	}
}

inline fun <R, E> Validated<R, E>.onFailure(
	block: (E) -> Unit
) {
	if (this is Validated.Invalid) {
		block.invoke(this.error)
	}
}