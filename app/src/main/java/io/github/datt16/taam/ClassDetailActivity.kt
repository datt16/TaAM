package io.github.datt16.taam

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.github.datt16.taam.databinding.ActivityClassDetailBinding

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

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_class_detail)
        binding.model = model
    }
}