package com.lumi.sdk.sample.screens

import android.os.Bundle
import com.lumi.commons.entities.Message
import com.lumi.sdk.entities.requests.SignTransactionRequest
import com.lumi.sdk.sample.R
import com.lumi.sdk.sample.application.App
import com.lumi.sdk.sample.interfaces.OnSignTransactionListener
import kotlinx.android.synthetic.main.activity_sign_transaction.*

class SignTransactionActivity : SignActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_transaction)

        setTitle(R.string.sign_transaction)

        App.appController.getAppComponent().inject(this)

        activitySignTransactionSign.setOnClickListener { sign() }

        activitySignTransactionSend.setOnCheckedChangeListener { _, isChecked ->
            activitySignTransactionHex.setHint(if (isChecked) R.string.hex_transaction else R.string.signed_transaction)
        }

        setOnSignTransactionListener(object : OnSignTransactionListener {
            override fun onSignTransaction(message: Message<*>) {
                activitySignTransactionHex.text = message.value as String
            }
        })
    }

    private fun sign() {
        val address = activitySignTransactionAddress.text.toString()

        if (!utils.checkAddress(address)) {
            showError(R.string.address_invalid)
            return
        }

        val chainId = activitySignTransactionChainId.text.toString()

        val nonce = activitySignTransactionNonce.text.toString()

        val gasPrice = activitySignTransactionGasPrice.text.toString()
        if (gasPrice.isEmpty()) {
            showError(R.string.gas_price_empty)
            return
        }
        val gasLimit = activitySignTransactionGasLimit.text.toString()
        if (gasLimit.isEmpty()) {
            showError(R.string.gas_limit_empty)
            return
        }

        val amount = activitySignTransactionAmount.text.toString()
        val data = activitySignTransactionData.text.toString()
        if (amount.isEmpty() && data.isEmpty()) {
            showError(R.string.amount_and_data_empty)
            return
        }

        val sendTransaction = activitySignTransactionSend.isChecked

        val requestBuilder = SignTransactionRequest.builder()
        requestBuilder.address(address)
                .chainId(chainId)
                .nonce(nonce)
                .amount(amount)       // wei
                .gasPrice(gasPrice)   // wei
                .gasLimit(gasLimit)   // wei
                .data(data)
                .send(sendTransaction)
        val request = requestBuilder.getRequest()
        lumi.execute(this, request)

    }
}