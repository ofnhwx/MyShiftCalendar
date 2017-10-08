package net.komunan.myshiftcalendar.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hugo.weaving.DebugLog
import net.komunan.myshiftcalendar.databinding.FragmentCalendarBinding
import net.komunan.myshiftcalendar.util.BaseFragment

@DebugLog
class CalendarFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun create(): CalendarFragment {
            return CalendarFragment()
        }
    }

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCalendarBinding.inflate(inflater!!, container, false).also {
            binding = it
        }.root
    }
}
