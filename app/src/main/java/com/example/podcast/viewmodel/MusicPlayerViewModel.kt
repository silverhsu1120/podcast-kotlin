package com.example.podcast.viewmodel

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

class MusicPlayerViewModel : BaseViewModel() {

    val elapsedTime = MutableLiveData<String>()
    val remainingTime = MutableLiveData<String>()
    val isPlaying = MutableLiveData<Boolean>()

    fun calculateElapsedTime(millis: Long) {
        val sdf = SimpleDateFormat("mm:ss", Locale.getDefault())
        val c = Calendar.getInstance()
        c.timeInMillis = millis
        elapsedTime.value = sdf.format(c.time)
    }

    fun calculateRemainingTime(millis: Long) {
        val sdf = SimpleDateFormat("mm:ss", Locale.getDefault())
        val c = Calendar.getInstance()
        c.timeInMillis = millis
        remainingTime.value = sdf.format(c.time)
    }

    fun onPlayerControl() {
        isPlaying.value?.let { isPlaying.value = !it }
    }
}
