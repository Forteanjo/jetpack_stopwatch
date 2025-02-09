package sco.carlukesoftware.jetpackstopwatch.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import sco.carlukesoftware.jetpackstopwatch.model.StopwatchViewModel

@Composable
fun StopwatchScreen(
    modifier: Modifier = Modifier,
    stopwatchViewModel: StopwatchViewModel = koinInject()
) {
    // Collect the elapsed time and running state
    val elapsedTime = stopwatchViewModel
        .elapsedTime
        .collectAsState()
    val isRunning = stopwatchViewModel
        .isRunning
        .collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(
                Alignment.Center
            )
    ) {
        Text(
            text = "Jetpack Stopwatch",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier
                .height(48.dp)
        )

        // Display the elapsed time
        Text(
            text = elapsedTime.value,
            style = MaterialTheme.typography.displayLarge,
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Row(
          horizontalArrangement = Arrangement
              .Center
        ) {
            // Start/Stop button
            Button(
                onClick = {
                    stopwatchViewModel.startStopwatch()
                }
            ) {
                Text(
                    text = if (isRunning.value) "Stop" else "Start"
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .width(24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Reset button
            Button(
                onClick = {
                    stopwatchViewModel.resetStopwatch()
                }
            ) {
                Text(
                    text = "Reset"
                )
            }
        }
    }
}
