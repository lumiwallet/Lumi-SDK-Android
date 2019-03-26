package  com.lumi.sdk.entities.requests

import android.net.Uri
import com.lumi.commons.entities.Message
import com.lumi.sdk.interfaces.Request

open class SignRequest : Request {
    private val scheme = "lumi"
    private var method: Uri
    private var message: Message<*>? = null

    init {
        method = createMethod()
    }

    override fun getMethod(): Uri {
        return method
    }

    override fun getMessage(): Message<*>? {
        return message
    }

    open fun getMethodName(): String {
        return "unknown"
    }

    fun setMessage(message: Message<*>?) {
        this.message = message
    }

    private fun createMethod(): Uri {
        val uriBuilder = Uri.Builder()
                .scheme(scheme)
                .authority(getMethodName())
        return uriBuilder.build()
    }
}