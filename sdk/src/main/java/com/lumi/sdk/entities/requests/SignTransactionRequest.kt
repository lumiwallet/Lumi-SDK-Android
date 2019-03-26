package com.lumi.sdk.entities.requests

import com.google.gson.Gson
import com.lumi.commons.entities.Message
import com.lumi.commons.enumerations.SignType
import com.lumi.sdk.entities.Transaction

class SignTransactionRequest : SignRequest() {

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    override fun getMethodName(): String {
        return SignType.SIGN_TRANSACTION.getIdentifier()
    }

    class Builder {
        private var id: Long = System.currentTimeMillis()
        private var address: String = ""
        private var amount: String = ""
        private var nonce: String = ""
        private var data: String = ""
        private var gasPrice: String = ""
        private var gasLimit: String = ""
        private var chainId: String = ""
        private var send = false

        fun id(id: Long): Builder {
            this.id = id
            return this
        }

        fun address(address: String): Builder {
            this.address = address
            return this
        }

        fun amount(amount: String): Builder {
            this.amount = amount
            return this
        }

        fun nonce(nonce: String): Builder {
            this.nonce = nonce
            return this
        }

        fun data(data: String): Builder {
            this.data = data
            return this
        }

        fun gasPrice(gasPrice: String): Builder {
            this.gasPrice = gasPrice
            return this
        }

        fun gasLimit(gasLimit: String): Builder {
            this.gasLimit = gasLimit
            return this
        }

        fun chainId(chainId: String): Builder {
            this.chainId = chainId
            return this
        }

        fun send(send: Boolean): Builder {
            this.send = send
            return this
        }

        fun getRequest(): SignTransactionRequest {
            val request = SignTransactionRequest()
            val transaction = Transaction()
            transaction.id = id
            transaction.address = address
            transaction.amount = amount
            transaction.nonce = nonce
            transaction.data = data
            transaction.gasPrice = gasPrice
            transaction.gasLimit = gasLimit
            transaction.gasLimit = gasLimit
            transaction.chainId = chainId
            transaction.send = send
            val message = Gson().toJson(transaction)
            request.setMessage(Message(message, id, SignType.SIGN_TRANSACTION))
            return request
        }
    }
}