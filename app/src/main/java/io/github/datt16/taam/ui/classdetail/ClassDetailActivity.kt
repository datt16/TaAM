package io.github.datt16.taam.ui.classdetail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.datt16.taam.Application
import io.github.datt16.taam.R
import io.github.datt16.taam.databinding.ActivityClassDetailBinding
import io.github.datt16.taam.ui.attendance.AddAttendanceDialogFragment

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

        model.attendances.observe(this, Observer {
            model.setAttendanceItems(it)
        })

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_class_detail)
        binding.model = model
        binding.lifecycleOwner = this

        binding.attendanceGoBtn.setOnClickListener {
            val dialog = AddAttendanceDialogFragment()
            dialog.show(supportFragmentManager, dialog.TAG)
        }
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)
    }
}