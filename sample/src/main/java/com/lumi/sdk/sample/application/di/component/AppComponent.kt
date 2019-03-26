package com.lumi.sdk.sample.application.di.component

import com.lumi.sdk.sample.application.di.module.LumiModule
import com.lumi.sdk.sample.application.di.module.UtilsModule
import com.lumi.sdk.sample.screens.SignActivity
import dagger.Component

@AppScope
@Component(modules = [LumiModule::class, UtilsModule::class])

interface AppComponent {

    fun inject(activity: SignActivity): SignActivity
}