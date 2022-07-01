package com.square.employeedirectory

import android.app.Application
import com.square.employeedirectory.di.dataModule
import com.square.employeedirectory.di.networkModule
import com.square.employeedirectory.di.useCaseModule
import com.square.employeedirectory.di.viewModelModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.BuildConfig
import org.kodein.di.android.androidCoreModule
import timber.log.Timber

class EmployeeDirectoryApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(androidCoreModule(this@EmployeeDirectoryApplication))
        import(networkModule)
        import(dataModule(applicationContext))
        import(useCaseModule)
        import(viewModelModule)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}