package net.komunan.myshiftcalendar.ui.template

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import net.komunan.myshiftcalendar.database.CalendarDatabase
import net.komunan.myshiftcalendar.database.entity.Template

class FragmentTemplateEditViewModel internal constructor(id: Long) : ViewModel() {
    val template: LiveData<Template>

    init {
        if (id == 0L) {
            template = MutableLiveData<Template>().apply {
                value = Template()
            }
        } else {
            template = CalendarDatabase.instance.templateDao().find(id)
        }
    }

    class Factory(private val id: Long) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return FragmentTemplateEditViewModel(id) as T
        }
    }
}
