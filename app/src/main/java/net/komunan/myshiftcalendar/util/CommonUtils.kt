package net.komunan.myshiftcalendar.util

import android.graphics.Point
import android.view.View
import android.view.WindowManager

import hugo.weaving.DebugLog

@DebugLog
object CommonUtils {
    fun getDisplaySize(windowManager: WindowManager): Point {
        return Point().apply {
            windowManager.defaultDisplay.getSize(this)
        }
    }

    fun getViewSize(view: View): Point {
        return Point().apply {
            view.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            set(view.measuredWidth, view.measuredHeight)
        }
    }
}
