package com.example.callingappnew.data.repository

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.CallLog
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.callingappnew.data.model.CallLogItem
import com.example.callingappnew.data.model.Contact

class CallRepository(private val context: Context) {

    fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Call permission not granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllContacts(): List<CallLogItem> {

        val list = mutableListOf<CallLogItem>()

        val cursor = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            null,
            null,
            null,
            CallLog.Calls.DATE + " DESC"
        )

        cursor?.use {

            val numberIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
            val nameIndex = it.getColumnIndex(CallLog.Calls.CACHED_NAME)
            val typeIndex = it.getColumnIndex(CallLog.Calls.TYPE)
            val dateIndex = it.getColumnIndex(CallLog.Calls.DATE)
            val durationIndex = it.getColumnIndex(CallLog.Calls.DURATION)

            while (it.moveToNext()) {

                val number = if (numberIndex != -1) it.getString(numberIndex) ?: "" else ""

                val name = if (nameIndex != -1) it.getString(nameIndex) else null

                val type = if (typeIndex != -1) it.getInt(typeIndex) else 0
                val date = if (dateIndex != -1) it.getLong(dateIndex) else 0L
                val duration = if (durationIndex != -1) it.getString(durationIndex) ?: "0" else "0"

                list.add(
                    CallLogItem(
                        name = name ?: "",   // fallback handled later
                        number = number,
                        type = type,
                        date = date,
                        duration = duration
                    )
                )
            }
        }

        return list
    }
    fun getContacts(): List<Contact> {

        val list = mutableListOf<Contact>()
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null,
            null

        )

        cursor?.use {
            while (it.moveToNext()) {

                val name = it.getString(
                    it.getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                )


                val number = it.getString(
                    it.getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )

                list.add(Contact(name, number))

            }

        }
        return list

    }

}