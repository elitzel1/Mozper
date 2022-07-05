package com.example.android.mozper.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.android.mozper.presentation.base.MozperActivity
import com.example.android.mozper.presentation.dashboard.MainActivity

interface ViewRouter {
    fun toLogin()
}

class LoginActivity : MozperActivity(), ViewRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragmentView(
            LoginScreen.newInstance(),
            LoginScreen.TAG
        )
    }

    override fun toLogin() {
        val flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        MainActivity.startActivity(context = this, flags = flags)
    }


    companion object {

        fun startActivity(context: Context, flags: Int? = null) {
            val intent = Intent(context, LoginActivity::class.java)
            if (null != flags) {
                intent.flags = flags
            }
            context.startActivity(intent)
        }
    }
}