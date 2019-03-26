package com.lumi.sdk.sample.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.lumi.commons.Const.Companion.SIGN_ACTION
import com.lumi.commons.entities.Message
import com.lumi.commons.entities.SignInfo
import com.lumi.commons.entities.SignResponse
import com.lumi.commons.enumerations.SignStatus
import com.lumi.commons.enumerations.SignType
import com.lumi.sdk.Lumi
import com.lumi.sdk.sample.R
import com.lumi.sdk.sample.interfaces.OnSignMessageListener
import com.lumi.sdk.sample.interfaces.OnSignPersonalMessageListener
import com.lumi.sdk.sample.interfaces.OnSignTransactionListener
import com.lumi.sdk.sample.utils.Utils
import javax.inject.Inject


abstract class SignActivity : AppCompatActivity() {

    @Inject
    lateinit var lumi: Lumi

    @Inject
    lateinit var utils: Utils

    private var onSignMessageListener: OnSignMessageListener? = null
    private var onSignPersonalMessageListener: OnSignPersonalMessageListener? = null
    private var onSignTransactionListener: OnSignTransactionListener? = null

    private val signReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            parseResponse(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        registerReceiver(signReceiver, IntentFilter(SIGN_ACTION))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(signReceiver)
    }

    fun setOnSignMessageListener(listener: OnSignMessageListener?) {
        onSignMessageListener = listener
    }

    fun setOnSignPersonalMessageListener(listener: OnSignPersonalMessageListener?) {
        onSignPersonalMessageListener = listener
    }

    fun setOnSignTransactionListener(listener: OnSignTransactionListener?) {
        onSignTransactionListener = listener
    }

    fun showError(message: Int) {
        showError(getString(message))
    }

    private fun showError(message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)
        builder.setPositiveButton(R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun parseResponse(response: Intent?) {
        val signResponse: SignResponse? = lumi.getResponse(response)
        if (signResponse is SignResponse) {
            val message = signResponse.message
            val signInfo = signResponse.signInfo
            if (message is Message<*> && signInfo is SignInfo) {
                when (signInfo.status) {
                    SignStatus.SIGN_SUCCESSFULLY -> {
                        val signType = message.type
                        when (signType) {
                            SignType.SIGN_MESSAGE -> {
                                onSignMessageListener?.onSignMessage(message)
                            }
                            SignType.SIGN_PERSONAL_MESSAGE -> {
                                onSignPersonalMessageListener?.onSignPersonalMessage(message)
                            }
                            SignType.SIGN_TRANSACTION -> {
                                onSignTransactionListener?.onSignTransaction(message)
                            }
                            else -> {
                                showError(R.string.sing_message_type_not_supported)
                            }
                        }
                    }

                    SignStatus.SIGN_CANCELED -> {
                        showError(R.string.sign_canceled)
                    }

                    SignStatus.SIGN_ERROR -> {
                        showError(signInfo.description)
                    }
                }
            } else {
                showError(R.string.sign_message_empty)
            }
        } else {
            showError(R.string.sign_message_empty)
        }
    }
}