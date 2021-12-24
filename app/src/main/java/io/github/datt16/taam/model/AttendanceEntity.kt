package io.github.datt16.taam.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "attendances")
data class Attendance(
    @PrimaryKey val id: Int = 0, // id
    val target: Int, // 教科オブジェクトのid
    val created: Date, // 作成時のタイムスタンプ
    val status: String,
    val memo: String?
)
