package io.github.datt16.taam.ui.classdetail

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.datt16.taam.Application
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.data.AttendanceRepository
import io.github.datt16.taam.data.ClassRepository
import io.github.datt16.taam.model.AttendanceEntity
import io.github.datt16.taam.model.ClassEntity
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.coroutines.CoroutineContext

// TODO: Repository を ViewModel のコンストラクタ内で定義
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

    // TODO: 後でLiveDataインスタンスを代入するのではなく、初期化時にRepositoryのメソッドを呼び出すようにする
    // INFO: attendanceの変更通知は取得できるけど、データ自体が更新されない
    var attendances: LiveData<List<AttendanceEntity>> = MutableLiveData(emptyList())

    private val _attendancePresenceCount: MutableLiveData<Int> = MutableLiveData(0)
    val attendancePresenceCount: LiveData<Int> = _attendancePresenceCount

    private val _attendanceCanceledCount: MutableLiveData<Int> = MutableLiveData(0)
    val attendanceCanceledCount: LiveData<Int> = _attendanceCanceledCount

    private val _attendanceAbsenceCount: MutableLiveData<Int> = MutableLiveData(0)
    val attendanceAbsenceCount: LiveData<Int> = _attendanceAbsenceCount


    var lastAttendanceDate: Date = Date(0)
    var nextClassIndex = 0
    private var classId = 0

    init {
        classRepository = ClassRepository(AppDatabase.getDatabase(app, scope).classDao())
        attendanceRepository =
            AttendanceRepository(AppDatabase.getDatabase(app, scope).attendanceDao())
        this.classId = classId

        val id = this.classId.toLong()

        runBlocking {
            withContext(Dispatchers.IO) {
                val deferredClassData = async { classRepository.getClassById(id) }
                classData = deferredClassData.await()

                val deferredAttendance = async { attendanceRepository.getRecordByClassId(id) }
                attendances = deferredAttendance.await()
            }
        }
    }

    fun setAttendanceItems(source: List<AttendanceEntity>) {
        _attendanceAbsenceCount.value = 0
        _attendancePresenceCount.value = 0
        _attendanceCanceledCount.value = 0

        if (source.isEmpty()) return
        source.forEach {
            when (it.status) {
                AttendanceEntity.ok -> _attendancePresenceCount.value =
                    _attendancePresenceCount.value?.plus(1)
                AttendanceEntity.absence -> _attendanceAbsenceCount.value =
                    _attendanceAbsenceCount.value?.plus(1)
                AttendanceEntity.cancel -> _attendanceCanceledCount.value =
                    _attendanceCanceledCount.value?.plus(1)
            }
        }
        lastAttendanceDate = source.last().created
        nextClassIndex = source.size + 1
    }

    fun addAttendance(status: String, index: Int) {
        val date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
        val attendanceItem =
            AttendanceEntity(targetId = 0, created = date, status = status, index = index - 1)

        runBlocking {
            withContext(Dispatchers.IO) {
                attendanceRepository.insert(attendanceItem)
            }
        }

//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                attendanceRepository.insert(attendanceItem)
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
