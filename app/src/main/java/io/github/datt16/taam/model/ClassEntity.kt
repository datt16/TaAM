package io.github.datt16.taam.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "classes")
data class ClassEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val description: String?,
    val name: String?,

    // フィールド名と別の名前をテーブルの列名に指定したい場合は@ColumnInfo()を使う
    // @ColumnInfo(name = "full_name") val fullName: String?,
) {
    companion object {
        fun create4insert(description: String?, name: String?): ClassEntity {
            return ClassEntity(0, description, name)
        }
    }
}
