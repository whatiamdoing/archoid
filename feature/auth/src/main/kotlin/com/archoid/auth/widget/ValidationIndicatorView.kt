package com.archoid.auth.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.auth.databinding.WidgetValidationIndicationBinding
import com.archoid.auth.R

class ValidationIndicatorView @JvmOverloads constructor(
	context: Context,
	attributeSet: AttributeSet? = null,
	defStyleAttr: Int = 0
): LinearLayout(context, attributeSet, defStyleAttr) {

	init {
		inflate(context, R.layout.widget_validation_indication, this)
	}

	private val viewBinding by viewBinding(WidgetValidationIndicationBinding::bind)

	private var valid: Boolean = false
		set(value) {
			if (value) {
				setValidState()
			} else {
				setInvalidState()
			}
			field = value
		}

	private fun setValidState() = with(viewBinding.ivValidation) {
		setImageDrawable(
			ContextCompat.getDrawable(
				context,
				R.drawable.ic_valid
			)
		)
		imageTintList = ColorStateList.valueOf(
			ContextCompat.getColor(
				context,
				com.archoid.resources.R.color.success
			)
		)
	}

	private fun setInvalidState() = with(viewBinding.ivValidation) {
		setImageDrawable(
			ContextCompat.getDrawable(
				context,
				R.drawable.ic_invalid
			)
		)
		imageTintList = ColorStateList.valueOf(
			ContextCompat.getColor(
				context,
				com.archoid.resources.R.color.failure
			)
		)
	}

	fun setValidationState(valid: Boolean) {
		this.valid = valid
	}

	init {
		val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.ValidationIndicatorView)
		viewBinding.tvValidation.text = attrs.getString(R.styleable.ValidationIndicatorView_android_text)
		attrs.recycle()
	}

}