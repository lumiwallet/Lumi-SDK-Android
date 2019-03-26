package com.lumi.sdk.entities

import com.google.gson.annotations.SerializedName

class Transaction {

    @SerializedName("id")
    var id: Long = -1

    @SerializedName("address")
    var address: String = ""

    @SerializedName("amount")
    var amount: String = ""

    @SerializedName("nonce")
    var nonce: String = ""

    @SerializedName("data")
    var data: String = ""

    @SerializedName("gasPrice")
    var gasPrice: String = ""

    @SerializedName("gasLimit")
    var gasLimit: String = ""

    @SerializedName("chainId")
    var chainId: String = ""

    @SerializedName("send")
    var send: Boolean = false
}