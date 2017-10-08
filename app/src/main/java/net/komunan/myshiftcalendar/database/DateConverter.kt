package net.komunan.myshiftcalendar.database

import android.arch.persistence.room.TypeConverter

import org.threeten.bp.LocalTime

class DateConverter {
    @TypeConverter
    fun fromTimeToDateTime(time: Int?): LocalTime? = if (time == null) null else LocalTime.ofSecondOfDay(time.toLong())

    @TypeConverter
    fun fromDateTimeToTime(time: LocalTime?): Int? = time?.toSecondOfDay()
}
