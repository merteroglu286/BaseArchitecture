package com.android.core.components.base_components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.android.core.R
import com.android.core.databinding.ComponentHeaderBinding

class HeaderComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentHeaderBinding =
        ComponentHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    private var title = ""

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HeaderComponent)

        attributes.apply {
            title = getString(R.styleable.HeaderComponent_header_title) ?: ""


            with(binding) {
                titleTextView.text = title
            }

            binding.backButton.setOnClickListener { findNavController().navigateUp() }

            recycle()
        }
    }

    fun changeTitle(title: String) {
        this.title = title
        binding.titleTextView.text = title
    }
}