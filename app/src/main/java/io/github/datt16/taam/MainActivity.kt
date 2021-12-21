package io.github.datt16.taam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.model.ClassEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.getInstance()?.let { db ->
            val dao = db.classDao()
            dao.saveClass(ClassEntity.create4insert("sample txt", "Sample_Class1"))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
        }

        AppDatabase.getInstance()?.let { db ->
            val dao = db.classDao()
            dao.findById(1).flatMapCompletable { setText(it) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

    }

    private fun setText(obj: ClassEntity): Completable {
        Log.d("setText", "name ==== ${obj.name}")
        return Completable.complete()
    }
}