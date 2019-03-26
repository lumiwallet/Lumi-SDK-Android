package com.lumi.sdk.sample.interfaces

import com.lumi.commons.entities.Message

interface OnSignPersonalMessageListener {
    fun onSignPersonalMessage(message: Message<*>)
}