package com.elslode.coroutinesforprofessional

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    fun method() {
        val job = viewModelScope.launch(Dispatchers.Default) {
            var count = 0
            for (i in 0 until 100_000_000) {
                for (j in 0 until 100) {
                    count++
                    ensureActive()
                }
            }
        }

        job.invokeOnCompletion {
            Log.d(TAG, "method: coroutine was canceled $it")
        }
        viewModelScope.launch {
            job.cancel()
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}