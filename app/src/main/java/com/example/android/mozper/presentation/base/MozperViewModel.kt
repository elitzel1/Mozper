package com.example.android.mozper.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MozperViewModel @Inject constructor() : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorStateEmitter.postValue(throwable)
    }

    private val _errorStateEmitter = MutableLiveData<Throwable>()
    val errorStateEmitter: LiveData<Throwable> get() = _errorStateEmitter


    fun launchIo(launchBody: suspend () -> Unit): Job = viewModelScope.launch(
        Dispatchers.IO + coroutineExceptionHandler
    ) { launchBody.invoke() }
}
