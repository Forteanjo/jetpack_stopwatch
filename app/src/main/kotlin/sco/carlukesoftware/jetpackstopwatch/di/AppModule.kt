package sco.carlukesoftware.jetpackstopwatch.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import sco.carlukesoftware.jetpackstopwatch.model.StopwatchViewModel

val appModule = module {

    viewModel { StopwatchViewModel() }

}
