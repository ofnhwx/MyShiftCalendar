package net.komunan.myshiftcalendar.event

import org.greenrobot.eventbus.EventBus

import hugo.weaving.DebugLog

class MoveToTemplateEditEvent private constructor(val id: Long) {
    companion object {
        @DebugLog
        @JvmStatic
        fun execute(id: Long) = EventBus.getDefault().post(MoveToTemplateEditEvent(id))
    }
}
