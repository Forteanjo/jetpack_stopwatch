package sco.carlukesoftware.jetpackstopwatch.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sco.carlukesoftware.jetpackstopwatch.di.appModule

/**
 * [StopwatchApplication] is the main application class for the Stopwatch app.
 *
 * This class extends the Android [Application] class and is responsible for:
 * 1. Initializing Koin for dependency injection.
 * 2. Providing application-wide context and configuration.
 *
 * It's the entry point for the application and sets up the Koin dependency injection framework
 * during the application's creation.
 */
class StopwatchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StopwatchApplication)
            androidLogger()

            modules(appModule)
        }
    }
}
