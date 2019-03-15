package com.pascalhow.myskyscanner.utils

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.widget.ProgressBar

fun ProgressBar.setColour(context: Context, colourId: Int) {
    this.indeterminateDrawable
        .setColorFilter(
            ContextCompat.getColor(context, colourId),
            PorterDuff.Mode.SRC_IN
        )
}
