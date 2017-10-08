package net.komunan.myshiftcalendar.ui.preference

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

import hugo.weaving.DebugLog

@DebugLog
class PreferenceFragment : PreferenceFragmentCompat() {
    companion object {
        @JvmStatic
        fun create(): PreferenceFragment {
            return PreferenceFragment()
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle, rootKey: String) {
    }
}
