package io.github.datt16.taam

import android.app.Application

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        this.deleteDatabase("APP_DATABASE")
    }
}
