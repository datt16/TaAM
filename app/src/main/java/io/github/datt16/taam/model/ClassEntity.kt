package io.github.datt16.taam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "classes")
data class ClassEntity(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,

    val description: String?,

    @ColumnInfo(name = "index")
    val currentIndex: Int = 0

    // フィールド名と別の名前をテーブルの列名に指定したい場合は@ColumnInfo()を使う
    // @ColumnInfo(name = "full_name") val fullName: String?,
) {
    companion object {
        fun create4insert(name: String, description: String?): ClassEntity {
            return ClassEntity(0, name, description, 0)
        }
    }
}
