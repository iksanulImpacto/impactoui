package com.impacto.impactoui.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impacto.impactoui.textStyle.AppTextStyle

@Composable
fun AppKeyboardDoneBar(
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(Color(0xFFF1F1F1)) // Warna abu-abu terang standar toolbar iOS
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Done",
            style = AppTextStyle.MediumNormal.copy(
                color = Color(0xFF007AFF), // Biru khas iOS
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .clickable { onDone() }
                .padding(vertical = 8.dp)
        )
    }
}
