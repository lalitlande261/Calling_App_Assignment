package com.example.callingappnew.presentation.componants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.callingappnew.data.model.Contact

@Composable
fun ContactItem(
    contact: Contact,
    onCallClick: () -> Unit
) {
Column( modifier = Modifier
    .fillMaxWidth()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCallClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(text = contact.name, fontSize = 18.sp)

            Text(
                text = contact.number,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Icon(
            imageVector = Icons.Default.Call,
            contentDescription = "Call"
        )
    }
        HorizontalDivider()
    }
}