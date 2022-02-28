package io.github.datt16.taam.ui.classdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.github.datt16.taam.Application
import io.github.datt16.taam.ui.classdetail.ClassDetailViewModel
import io.github.datt16.taam.ui.classdetail.ClassDetailViewModelFactory
import io.github.datt16.taam.R
import io.github.datt16.taam.databinding.ActivityClassDetailBinding
import io.github.datt16.taam.ui.attendance.AddAttendanceFragment

class ClassDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.getIntExtra("ID", 0)
        val model by viewModels<ClassDetailViewModel> {
            ClassDetailViewModelFactory(
                Application(),
                id
            )
        }

        val attendanceFragment = AddAttendanceFragment()
        val transaction = supportFragmentManager.beginTransaction()

        Log.d("CDA:onCreate", model.hashCode().toString())
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_class_detail)
        binding.model = model

        transaction.add(R.id.attendanceView, attendanceFragment)
        transaction.commit()
    }
}