package io.github.datt16.taam.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "attendances")
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // id
    val targetId: Long, // 教科オブジェクトのid
    val created: Date, // 作成時のタイムスタンプ
    val status: String, // ok | absence | cancel
    val memo: String? = "",
    val index: Int, // 授業時間数
    val isExtra: Boolean = false, // 補講かどうか
    val except: Boolean = false // 公欠かどうか
) {
    companion object {
        const val ok = "presence"
        const val absence = "absence"
        const val cancel = "cancel"
    }
}
