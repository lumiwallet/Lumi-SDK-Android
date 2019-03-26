package com.lumi.sdk.sample.interfaces

import com.lumi.commons.entities.Message

interface OnSignTransactionListener {
    fun onSignTransaction(message: Message<*>)
}