package io.github.datt16.taam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.model.ClassEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

//    private lateinit var classListViewModel: ClassListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        classListViewModel = ViewModelProvider(
//            this,
//            ClassListViewModelFactory(this.application as Application)
//        ).get(ClassListViewModel::class.java)
        setContentView(R.layout.activity_main)

//        classListViewModel.allClasses.observe(this, Observer { cls ->
//            cls.let { Log.d("Main::Observer", it.toString()) }
//        })
    }
}
