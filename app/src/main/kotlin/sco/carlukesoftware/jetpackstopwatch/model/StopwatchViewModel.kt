package sco.carlukesoftware.jetpackstopwatch.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class StopwatchViewModel : ViewModel() {

    private val stopwatch = Stopwatch()

    // StateFlow to hold the current elapsed time
    private val _elapsedTime = MutableStateFlow("00:00.000")
    val elapsedTime: StateFlow<String> get() = _elapsedTime

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> get() = _isRunning

    // Start or stop the stopwatch
    fun startStopwatch() {
        if (_isRunning.value) {
            stopwatch.stop()
        } else {
            startTimeFlow()
        }

        _isRunning.value = !_isRunning.value
        Log.i(this.javaClass.simpleName, "isRunning: ${_isRunning.value}")
    }

    // Reset the stopwatch
    fun resetStopwatch() {
        stopwatch.reset()
        _elapsedTime.value = formatElapsedTime(0, 0)
    }

    // Launch the stopwatch flow to collect and update the time
    private fun startTimeFlow() {
        viewModelScope.launch {
            stopwatch.start().collect { (seconds, milliseconds) ->
                _elapsedTime.value = formatElapsedTime(seconds, milliseconds)
            }
        }
    }

    // Format time as MM:SS.mmm
    private fun formatElapsedTime(seconds: Int, milliseconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format(
            Locale.ROOT, "%02d:%02d.%03d",
            minutes, remainingSeconds, milliseconds
        )
    }

}
