package com.lumi.sdk.interfaces

import android.net.Uri
import com.lumi.commons.entities.Message

interface Request {

    fun getMethod(): Uri

    fun getMessage(): Message<*>?
}