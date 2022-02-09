package io.github.datt16.taam

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.data.ClassRepository
import io.github.datt16.taam.model.ClassEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClassDetailViewModel(app: Application, classId: Int) : AndroidViewModel(app) {

    // リポジトリ
    private val repository: ClassRepository

    // 非同期処理関連
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    lateinit var classData: ClassEntity


    init {
        val dao = AppDatabase.getDatabase(app, scope).classDao()
        repository = ClassRepository(dao)
        loadData(classId.toLong())
    }

    private fun loadData(classId: Long) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                classData = repository.getClassById(classId)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
