package io.github.datt16.taam

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.data.ClassRepository
import io.github.datt16.taam.model.ClassEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ClassListViewModel(app: Application) : AndroidViewModel(app) {

    // リポジトリ
    private val repository: ClassRepository

    // 非同期処理関連
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val allClasses: LiveData<List<ClassEntity>>

    init {
        val classDao = AppDatabase.getDatabase(app, scope).classDao()
        repository = ClassRepository(classDao)
        allClasses = repository.allClasses
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}