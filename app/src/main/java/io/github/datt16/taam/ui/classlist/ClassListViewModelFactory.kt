package io.github.datt16.taam.ui.classlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.datt16.taam.Application

class ClassListViewModelFactory(
    private var application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassListViewModel(application) as T
    }
}
