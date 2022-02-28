package io.github.datt16.taam.ui.classdetail

import androidx.lifecycle.AndroidViewModel
import io.github.datt16.taam.Application
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.data.AttendanceRepository
import io.github.datt16.taam.data.ClassRepository
import io.github.datt16.taam.model.AttendanceEntity
import io.github.datt16.taam.model.ClassEntity
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class ClassDetailViewModel(app: Application, classId: Int) : AndroidViewModel(app) {

    // リポジトリ
    private val classRepository: ClassRepository
    private val attendanceRepository: AttendanceRepository

    // 非同期処理関連
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    // データソース
    lateinit var classData: ClassEntity
    lateinit var attendances: List<AttendanceEntity>

    var attendancePresenceCount = 0
    var attendanceCanceledCount = 0
    var attendanceAbsenceCount = 0
    var lastAttendanceDate: Date = Date(0)

    init {
        classRepository = ClassRepository(AppDatabase.getDatabase(app, scope).classDao())
        attendanceRepository =
            AttendanceRepository(AppDatabase.getDatabase(app, scope).attendanceDao())
        loadData(classId.toLong())
    }

    private fun loadData(classId: Long) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                classData = classRepository.getClassById(classId)
                attendances = attendanceRepository.getRecordByClassId(classId)
                setAttendanceItems(attendances)
            }
        }
    }

    private fun setAttendanceItems(source: List<AttendanceEntity>) {
        if (source.isEmpty()) return
        source.forEach {
            when (it.status) {
                AttendanceEntity.ok -> attendancePresenceCount++
                AttendanceEntity.absence -> attendanceAbsenceCount++
                AttendanceEntity.cancel -> attendanceCanceledCount++
            }
        }
        lastAttendanceDate = source.last().created
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
