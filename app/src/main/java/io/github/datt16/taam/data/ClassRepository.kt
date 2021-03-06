package io.github.datt16.taam.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import io.github.datt16.taam.model.ClassEntity

class ClassRepository(private val classDao: ClassDao) {
    val allClasses: LiveData<List<ClassEntity>> = classDao.loadAllClasses()

    @WorkerThread
    fun insert(item: ClassEntity) {
        classDao.saveClass(item)
    }

    @WorkerThread
    fun getClassById(id: Long):ClassEntity {
        return classDao.findById(id)
    }
}