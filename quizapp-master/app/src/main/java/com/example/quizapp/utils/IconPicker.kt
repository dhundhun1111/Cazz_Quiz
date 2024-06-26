package com.example.quizapp.utils

import com.example.quizapp.R

object IconPicker {

    val icons = arrayOf(
        R.drawable.ic_icon_1,
        R.drawable.ic_icon_2,
        R.drawable.ic_icon_3,
        R.drawable.ic_icon_4,
        R.drawable.ic_icon_5,
        R.drawable.ic_icon_6,
        R.drawable.ic_icon_7,
        R.drawable.ic_icon_8
    )
    private var currentIconIndex = 0

    fun getIcon(): Int {
        val icon = icons[currentIconIndex]
        currentIconIndex = (currentIconIndex + 1) % icons.size
        return icon
    }

    fun resetIconIndex() {
        currentIconIndex = 0
    }
}