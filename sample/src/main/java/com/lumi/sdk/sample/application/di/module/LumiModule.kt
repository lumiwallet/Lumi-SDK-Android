package com.lumi.sdk.sample.application.di.module

import com.lumi.sdk.Lumi
import com.lumi.sdk.sample.application.di.component.AppScope
import dagger.Module
import dagger.Provides

@Module
class LumiModule {

    @AppScope
    @Provides
    fun provideLumi(): Lumi {
        return Lumi()
    }
}