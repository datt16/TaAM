package io.github.datt16.taam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.datt16.taam.model.ClassEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ClassDao {
    // データの取得メソッド
    @Query("SELECT * FROM classes")
    fun loadAllClasses(): Single<List<ClassEntity>>

    @Query("SELECT * FROM classes WHERE id = :id")
    fun findById(id: Long): Maybe<ClassEntity>

    // 挿入メソッド
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveClass(classEntity: ClassEntity): Completable
}
