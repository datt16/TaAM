package io.github.datt16.taam.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import io.github.datt16.taam.model.AttendanceEntity
import io.github.datt16.taam.model.ClassEntity

class ClassRepository(private val classDao: ClassDao, private val attendanceDao: AttendanceDao) {
    val allClasses: LiveData<List<ClassEntity>> = classDao.loadAllClasses()

    @WorkerThread
    suspend fun addClass(item: ClassEntity) {
        classDao.saveClass(item)
    }

    suspend fun getAttendance(id: String): LiveData<List<AttendanceEntity>> {
        return attendanceDao.findByClass(id)
    }
}