package net.komunan.myshiftcalendar.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

import net.komunan.myshiftcalendar.ReleaseApplication
import net.komunan.myshiftcalendar.database.dao.TemplateDao
import net.komunan.myshiftcalendar.database.entity.Template

@Database(entities = arrayOf(Template::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class CalendarDatabase : RoomDatabase() {
    abstract fun templateDao(): TemplateDao

    companion object {
        val instance: CalendarDatabase = Room.databaseBuilder(
                ReleaseApplication.context!!,
                CalendarDatabase::class.java,
                CalendarDatabase::class.java.simpleName
        ).build()
    }
}
