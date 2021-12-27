package io.github.datt16.taam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ClassListViewModelFactory(
    private var application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassListViewModel(application) as T
    }
}