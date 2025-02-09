package sco.carlukesoftware.jetpackstopwatch.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sco.carlukesoftware.jetpackstopwatch.di.appModule

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
