package com.lumi.sdk

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.lumi.commons.Const.Companion.SIGN_ACTION
import com.lumi.commons.Const.Companion.SIGN_REQUEST
import com.lumi.commons.Const.Companion.SIGN_RESPONSE
import com.lumi.commons.entities.SignResponse
import com.lumi.sdk.interfaces.Request

class Lumi {

    private val packageName: String = "com.lumicollect.android"
    private val className: String = "$packageName.receivers.SignBroadcastReceiver"

    fun execute(context: Context, request: Request) {
        if (isLumiAppInstalled(context)) {
            val intent = Intent()
            intent.component = ComponentName(packageName, className)
            intent.action = SIGN_ACTION
            intent.data = request.getMethod()
            intent.putExtra(SIGN_REQUEST, request.getMessage())
            context.sendBroadcast(intent)
        } else {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.market_address))))
            } catch (exception: ActivityNotFoundException) {
                context.startActivity(
                        Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(context.getString(R.string.google_play_address))
                        )
                )
            }
        }
    }

    fun getResponse(intent: Intent?): SignResponse? {
        return intent?.getParcelableExtra(SIGN_RESPONSE)
    }

    private fun isLumiAppInstalled(context: Context): Boolean {
        return try {
            context.packageManager.getPackageGids(packageName)
            true
        } catch (exception: PackageManager.NameNotFoundException) {
            false
        }
    }
}