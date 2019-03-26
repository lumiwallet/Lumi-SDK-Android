package com.lumi.sdk.sample.screens

import android.os.Bundle
import com.lumi.commons.entities.Message
import com.lumi.sdk.entities.requests.SignPersonalMessageRequest
import com.lumi.sdk.sample.R
import com.lumi.sdk.sample.application.App
import com.lumi.sdk.sample.interfaces.OnSignPersonalMessageListener
import kotlinx.android.synthetic.main.activity_sign_personal_message.*

class SignPersonalMessageActivity : SignActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_personal_message)

        setTitle(R.string.sign_personal_message)

        App.appController.getAppComponent().inject(this)

        activitySignPersonalMessageSign.setOnClickListener { sign() }

        setOnSignPersonalMessageListener(object : OnSignPersonalMessageListener {
            override fun onSignPersonalMessage(message: Message<*>) {
                activitySignPersonalMessageHex.text = message.value as String
            }
        })
    }

    private fun sign() {
        val message = activitySignPersonalMessage.text.toString()
        if (message.isEmpty()) {
            showError(R.string.message_empty)
        } else {
            val hexMessage = utils.convertStringToHex(message)
            val requestBuilder = SignPersonalMessageRequest.builder()
            requestBuilder.message(hexMessage)
            val request = requestBuilder.getRequest()
            lumi.execute(this, request)
        }
    }
}