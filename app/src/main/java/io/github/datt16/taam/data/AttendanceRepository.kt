package io.github.datt16.taam.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import io.github.datt16.taam.model.AttendanceEntity
import io.github.datt16.taam.model.ClassEntity
import java.util.*

class AttendanceRepository(private val attendanceDao: AttendanceDao) {
    val allAttendanceRecords: LiveData<List<AttendanceEntity>> =
        attendanceDao.getAllAttendanceRecord()

    @WorkerThread
    suspend fun insert(item: AttendanceEntity) {
        attendanceDao.insertAttendanceRecord(item)
    }

    fun getRecordByClassId(id: Long): LiveData<List<AttendanceEntity>> {
        return attendanceDao.findByClass(id)
    }

    fun getRecordByDate(date: Date): List<AttendanceEntity> {
        return attendanceDao.findAttendanceRecordOnDate(date)
    }

}
