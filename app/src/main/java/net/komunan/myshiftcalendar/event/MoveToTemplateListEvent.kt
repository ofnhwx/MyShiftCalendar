package net.komunan.myshiftcalendar.event

import hugo.weaving.DebugLog
import org.greenrobot.eventbus.EventBus

class MoveToTemplateListEvent private constructor(){
    companion object {
        @DebugLog
        @JvmStatic
        fun execute() = EventBus.getDefault().post(MoveToTemplateListEvent())
    }
}
