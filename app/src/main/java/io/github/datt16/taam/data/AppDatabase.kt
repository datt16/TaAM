package io.github.datt16.taam.data

import android.content.Context
import android.util.Log
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.datt16.taam.model.ClassEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ClassEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun classDao(): ClassDao

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch(Dispatchers.IO) {
                    populateDatabase(it.classDao())
                }
            }
        }

        fun populateDatabase(dao: ClassDao) {
            dao.deleteAll()
            dao.saveClass(
                ClassEntity.create4insert(
                    "TEST1",
                    "this is test data. Add from DB CallBack."
                )
            )
            dao.saveClass(
                ClassEntity.create4insert(
                    "TEST2",
                    "this is test data. Add from DB CallBack."
                )
            )

        }
    }

    companion object {
        private const val DATABASE_NAME = "APP_DATABASE"
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            Log.d("DB", "get Instance called")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(AppDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}