package com.lumi.sdk.sample.screens


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lumi.sdk.sample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_sign_message.setOnClickListener {
            startActivity(Intent(this, SignMessageActivity::class.java))
        }

        activity_main_sign_personal_message.setOnClickListener {
            startActivity(Intent(this, SignPersonalMessageActivity::class.java))
        }

        activity_main_sign_transaction.setOnClickListener {
            startActivity(Intent(this, SignTransactionActivity::class.java))
        }
    }
}
