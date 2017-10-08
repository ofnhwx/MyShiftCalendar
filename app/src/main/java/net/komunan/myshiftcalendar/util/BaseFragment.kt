package net.komunan.myshiftcalendar.util

import android.support.v4.app.Fragment
import hugo.weaving.DebugLog

@DebugLog
abstract class BaseFragment : Fragment() {
    protected fun moveToPrevious() {
        fragmentManager.run {
            if (backStackEntryCount == 0) {
                activity.finish()
            } else {
                popBackStack()
            }
        }
    }
}
