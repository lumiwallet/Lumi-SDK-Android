package com.lumi.sdk.sample.application


import android.app.Application
import com.lumi.sdk.sample.application.di.component.AppComponent
import com.lumi.sdk.sample.application.di.component.DaggerAppComponent
import com.lumi.sdk.sample.application.di.module.LumiModule

class App : Application() {

    private var appComponent: AppComponent

    companion object {
        @JvmStatic
        lateinit var appController: App
    }

    init {
        appController = this
        appComponent = DaggerAppComponent.builder().lumiModule(LumiModule()).build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}