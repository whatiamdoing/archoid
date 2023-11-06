package com.archoid.core_ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.core_ui.R
import com.archoid.core_ui.databinding.WidgetLoadingButtonBinding
import com.archoid.global.utils.extensions.orFalse

class LoadingButton @JvmOverloads constructor(
	context: Context,
	attributeSet: AttributeSet? = null,
	defStyleAttr: Int = 0
): FrameLayout(context, attributeSet, defStyleAttr) {

	init {
		inflate(context, R.layout.widget_loading_button, this)
	}

	private val viewBinding by viewBinding(WidgetLoadingButtonBinding::bind)

	private var btnText: String?
	private var enabled: Boolean = isEnabled

	var isLoading: Boolean = false
		set(value) {
			field = value
			viewBinding.pbLoading.isVisible = value
			viewBinding.btn.isEnabled = enabled.takeIf { !value }.orFalse()
			invalidateBtn()
		}

	override fun setEnabled(enabled: Boolean) {
		viewBinding.btn.isEnabled = enabled
		this.enabled = enabled
	}

	override fun setOnClickListener(l: OnClickListener?) {
		viewBinding.btn.setOnClickListener(l)
	}

	private fun invalidateBtn() {
		viewBinding.btn.text = btnText.takeIf { !isLoading }
	}

	init {
		val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.LoadingButton)
		btnText = attrs.getString(R.styleable.LoadingButton_android_text)
		invalidateBtn()
		attrs.recycle()
	}

}