package io.github.datt16.taam.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.datt16.taam.model.AttendanceEntity
import io.github.datt16.taam.model.ClassEntity
import java.util.*

@Dao
interface AttendanceDao {
    // データの取得メソッド
    @Query("SELECT * FROM attendances")
    fun getAllAttendanceRecord(): LiveData<List<AttendanceEntity>>

    @Query("SELECT * FROM attendances WHERE id = :id")
    fun findById(id: Long): AttendanceEntity

    @Query("SELECT * FROM attendances WHERE targetId = :id")
    fun findByClass(id: Long): LiveData<List<AttendanceEntity>>

    @Query("SELECT * FROM attendances WHERE created = :targetDate")
    fun findAttendanceRecordOnDate(targetDate: Date): List<AttendanceEntity>

    // 挿入メソッド
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttendanceRecord(AttendanceEntity: AttendanceEntity)

    @Query("DELETE FROM attendances")
    fun deleteAll()
}


