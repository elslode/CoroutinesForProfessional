package com.elslode.coroutinesforprofessional

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.RuntimeException

class MainViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)

    fun method() {
        val childJobOne = coroutineScope.launch {
            delay(3000)
            Log.d(TAG, "first coroutine finished")
        }
        val childJobTwo = coroutineScope.launch {
            delay(2000)
            Log.d(TAG, "second coroutine finished")
        }
        val childJobThree = coroutineScope.launch {
            delay(1000)
            error()
            Log.d(TAG, "third coroutine finished")
        }
    }

    private fun error(){
        throw RuntimeException()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}