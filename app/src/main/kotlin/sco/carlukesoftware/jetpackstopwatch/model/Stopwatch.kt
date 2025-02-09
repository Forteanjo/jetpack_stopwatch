package sco.carlukesoftware.jetpackstopwatch.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

/**
 * A simple stopwatch class that can start, stop, and reset, and emits the elapsed time.
 *
 * This class uses a [MutableStateFlow] to emit the elapsed time as a pair of seconds and milliseconds.
 */
class Stopwatch {

    private var running: Boolean = false
    private var startTime: Long = 0L
    private var elapsedTime: Long = 0L

    // Use flow to emit the elapsed time in seconds
    private val elapsedTimeFlow = MutableStateFlow(0L)

    fun start() = flow {
        running = true
        startTime = System.currentTimeMillis() - elapsedTime
        while (running) {
            val currentTime = System.currentTimeMillis() - startTime
            val seconds = (currentTime / 1000).toInt()
            val milliseconds = (currentTime % 1000).toInt()
            emit(Pair(seconds, milliseconds))  // emit the pair of seconds and milliseconds
            delay(10)   // Emit every 10 milliseconds
        }
    }

    fun stop() {
        running = false
        elapsedTime = System.currentTimeMillis() - startTime
    }

    fun reset() {
        stop()
        elapsedTime = 0L
    }

}
