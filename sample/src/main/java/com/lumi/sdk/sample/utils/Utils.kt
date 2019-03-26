package com.lumi.sdk.sample.utils

import java.util.regex.Pattern

class Utils {

    fun convertStringToHex(string: String): String {
        return string.toByteArray().joinToString("") { String.format("%02x", it) }
    }

    fun checkAddress(address: String): Boolean {
        return Pattern.compile("^(0x)?[0-9a-fA-F]{40}\$").matcher(address.trim()).matches()
    }
}