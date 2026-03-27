package com.example.callingappnew.presentation.componants

import android.provider.CallLog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.callingappnew.data.model.CallLogItem
import com.example.callingappnew.utils.formatDate

@Composable
fun CallLogItemUI(
    log: CallLogItem,
    onCallClick: () -> Unit
) {

    val callType = when (log.type) {
        CallLog.Calls.INCOMING_TYPE -> "Incoming"
        CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
        CallLog.Calls.MISSED_TYPE -> "Missed"
        else -> "Unknown"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCallClick() }
            .padding(start = 16.dp, top = 4.dp, end = 16.dp,0.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                if (log.name.isEmpty()) {
                    Text(text = log.number, fontSize = 18.sp)
                } else {
                    Text(text = log.name, fontSize = 18.sp)
                    Text(text = log.number, fontSize = 14.sp)
                }

                Text(
                    text = callType,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Icon(
                Icons.Default.Call,
                contentDescription = "Call"
            )

        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = formatDate(log.date),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Text(
            text = "Duration: ${log.duration}s",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))

        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

    }
}