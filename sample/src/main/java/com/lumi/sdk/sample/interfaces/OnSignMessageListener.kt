package com.lumi.sdk.sample.interfaces

import com.lumi.commons.entities.Message

interface OnSignMessageListener {
    fun onSignMessage(message: Message<*>)
}