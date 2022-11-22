package com.kugemi.lime.presentataion.extensions

import android.content.res.Resources.getSystem
import androidx.compose.ui.unit.Dp

val Dp.px: Int get() = (this.value * getSystem().displayMetrics.density).toInt()