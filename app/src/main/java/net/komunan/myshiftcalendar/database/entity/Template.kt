package net.komunan.myshiftcalendar.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import net.komunan.myshiftcalendar.BR
import net.komunan.myshiftcalendar.database.CalendarDatabase
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

@Entity
class Template : BaseObservable() {
    companion object {
        private val timeFormatter = DateTimeFormatter.ofPattern("H:mm")
    }

    @get:Bindable
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        set(id) {
            field = id
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var title: String? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var startTime: LocalTime = LocalTime.MIN
        set(startTime) {
            field = startTime
            notifyPropertyChanged(BR.startTime)
            notifyPropertyChanged(BR.startTimeText)
            notifyPropertyChanged(BR.endTime)
            notifyPropertyChanged(BR.endTimeText)
        }

    val startTimeText: String
        @Bindable
        get() = startTime.format(timeFormatter)

    var endTime: LocalTime
        @Bindable
        get() = startTime.plusMinutes(duration.toLong())
        set(endTime) {
            this.duration = (endTime.hour - startTime.hour) * 60 + (endTime.minute - startTime.minute)
            notifyPropertyChanged(BR.duration)
            notifyPropertyChanged(BR.endTime)
            notifyPropertyChanged(BR.endTimeText)
        }

    val endTimeText: String
        @Bindable
        get() = startTime.plusMinutes(duration.toLong()).format(timeFormatter)

    @get:Bindable
    var duration: Int = 0
        set(duration) {
            field = duration
            notifyPropertyChanged(BR.duration)
            notifyPropertyChanged(BR.endTime)
            notifyPropertyChanged(BR.endTimeText)
        }

    @get:Bindable
    var isAllDay: Boolean = false
        set(allDay) {
            field = allDay
            notifyPropertyChanged(BR.allDay)
        }

    @get:Bindable
    var place: String? = null
        set(place) {
            field = place
            notifyPropertyChanged(BR.place)
        }

    @get:Bindable
    var description: String? = null
        set(description) {
            field = description
            notifyPropertyChanged(BR.description)
        }

    fun save() {
        val dao = CalendarDatabase.instance.templateDao()
        Thread {
            if (id == 0L) {
                id = dao.insert(this)
            } else {
                dao.update(this)
            }
        }.start()
    }
}
