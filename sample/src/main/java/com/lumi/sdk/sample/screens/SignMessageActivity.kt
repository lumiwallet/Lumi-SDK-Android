package com.lumi.sdk.sample.screens

import android.os.Bundle
import com.lumi.commons.entities.Message
import com.lumi.sdk.entities.requests.SignMessageRequest
import com.lumi.sdk.sample.R
import com.lumi.sdk.sample.application.App
import com.lumi.sdk.sample.interfaces.OnSignMessageListener
import kotlinx.android.synthetic.main.activity_sign_message.*

class SignMessageActivity : SignActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_message)
        App.appController.getAppComponent().inject(this)

        setTitle(R.string.sign_message)

        activitySignMessageSign.setOnClickListener { sign() }

        setOnSignMessageListener(object : OnSignMessageListener {
            override fun onSignMessage(message: Message<*>) {
                activitySignMessageHex.text = message.value as String
            }
        })
    }

    private fun sign() {
        val message = activitySignMessage.text.toString()
        if (message.isEmpty()) {
            showError(R.string.message_empty)
        } else {
            val hexMessage = utils.convertStringToHex(message)
            val requestBuilder = SignMessageRequest.builder()
            requestBuilder.message(hexMessage)
            val request = requestBuilder.getRequest()
            lumi.execute(this,request)
        }
    }
}