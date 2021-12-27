package io.github.datt16.taam.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.datt16.taam.model.ClassEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ClassDao {
    // データの取得メソッド
    @Query("SELECT * FROM classes")
    fun loadAllClasses(): LiveData<List<ClassEntity>>

    @Query("SELECT * FROM classes WHERE id = :id")
    fun findById(id: Long): Maybe<ClassEntity>

    // 挿入メソッド
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveClass(classEntity: ClassEntity): Completable

    @Query("DELETE FROM classes")
    fun deleteAll()
}
