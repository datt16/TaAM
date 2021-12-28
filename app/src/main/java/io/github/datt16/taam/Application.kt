package io.github.datt16.taam

import android.app.Application
import android.util.Log
import io.github.datt16.taam.data.AppDatabase

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        this.deleteDatabase("APP_DATABASE")
    }
}
