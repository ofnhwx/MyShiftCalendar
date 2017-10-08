package net.komunan.myshiftcalendar.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import net.komunan.myshiftcalendar.database.entity.Template

@Dao
interface TemplateDao {
    @Query("select * from Template")
    fun all(): LiveData<List<Template>>

    @Query("select * from Template where id = :id limit 1")
    fun find(id: Long): LiveData<Template>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(template: Template): Long

    @Insert
    fun insert(template: Template): Long

    @Update
    fun update(template: Template): Int

    @Delete
    fun delete(template: Template): Int
}
