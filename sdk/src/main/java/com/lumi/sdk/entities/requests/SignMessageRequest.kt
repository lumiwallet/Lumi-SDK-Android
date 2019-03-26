package com.lumi.sdk.entities.requests

import com.lumi.commons.entities.Message
import com.lumi.commons.enumerations.SignType

class SignMessageRequest : SignRequest() {

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    override fun getMethodName(): String {
        return SignType.SIGN_MESSAGE.getIdentifier()
    }

    class Builder {
        private var id: Long = System.currentTimeMillis()
        private var message: String = ""

        fun id(id: Long): Builder {
            this.id = id
            return this
        }

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun getRequest(): SignMessageRequest {
            val request = SignMessageRequest()
            request.setMessage(Message(message, id, SignType.SIGN_MESSAGE))
            return request
        }
    }
}