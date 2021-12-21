package io.github.datt16.taam

import android.app.Application
import io.github.datt16.taam.data.AppDatabase

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // AppDatabaseをビルドする
        this.deleteDatabase("sample.db")
        AppDatabase.init(this)
    }
}
