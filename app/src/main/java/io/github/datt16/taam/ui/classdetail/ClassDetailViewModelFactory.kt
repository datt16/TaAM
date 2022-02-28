package io.github.datt16.taam.ui.classdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.datt16.taam.Application

class ClassDetailViewModelFactory(private var application: Application, private val classId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassDetailViewModel(application, classId) as T
    }
}
