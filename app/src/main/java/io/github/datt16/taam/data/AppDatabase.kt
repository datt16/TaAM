package io.github.datt16.taam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.datt16.taam.model.ClassEntity

@Database(entities = [ClassEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun classDao(): ClassDao

    companion object {
        private const val DATABASE_NAME = "sample.db"
        private var instance: AppDatabase? = null

        fun init(context: Context) {
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
                .also { instance = it }
        }

        fun getInstance() = instance
    }
}