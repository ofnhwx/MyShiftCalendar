package net.komunan.myshiftcalendar.ui.template

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import net.komunan.myshiftcalendar.database.CalendarDatabase
import net.komunan.myshiftcalendar.database.entity.Template

class FragmentTemplateListViewModel : ViewModel() {
    val templates: LiveData<List<Template>>
        get() = CalendarDatabase.instance.templateDao().all()
}
