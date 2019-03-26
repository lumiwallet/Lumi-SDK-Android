package com.lumi.sdk.sample.application.di.module

import com.lumi.sdk.sample.application.di.component.AppScope
import com.lumi.sdk.sample.utils.Utils
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @AppScope
    @Provides
    fun provideUtils(): Utils {
        return Utils()
    }
}