package com.example.callingappnew.viewmodel

import android.content.Context
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CallStateManager(private val context: Context) {

    private val telephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    var isCallActive = mutableStateOf(false)
    var onCallEnded: (() -> Unit)? = null

    private var callback: TelephonyCallback? = null
    private var listener: PhoneStateListener? = null

    fun startListening() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            callback = object : TelephonyCallback(),
                TelephonyCallback.CallStateListener {

                override fun onCallStateChanged(state: Int) {
                    handleState(state)
                }
            }

            telephonyManager.registerTelephonyCallback(
                context.mainExecutor,
                callback!!
            )

        } else {

            listener = object : PhoneStateListener() {
                override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                    handleState(state)
                }
            }

            telephonyManager.listen(
                listener,
                PhoneStateListener.LISTEN_CALL_STATE
            )
        }
    }

    private fun handleState(state: Int) {
        when (state) {
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                isCallActive.value = true
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                isCallActive.value = false
                onCallEnded?.invoke()
            }

        }
    }

    fun stopListening() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            callback?.let {
                telephonyManager.unregisterTelephonyCallback(it)
            }
        } else {
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_NONE)
        }
    }
}