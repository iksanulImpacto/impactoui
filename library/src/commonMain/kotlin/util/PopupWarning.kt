package com.impacto.impactoui.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.impacto.impactoui.R
import com.impacto.impactoui.colors.AppColors
import kotlinx.coroutines.delay

enum class PopupType { SUCCESS, ERROR, WARNING }

data class PopupEvent(
    val message: String,
    val type: PopupType,
    val closeable: Boolean = false,
    val time: Long? = null

)

// ----------------------------
// Popup Manager (queue)
// ----------------------------
object PopupManager {
    private val _events = mutableStateListOf<PopupEvent>()
    val events: List<PopupEvent> get() = _events

    fun show(message: String, type: PopupType = PopupType.SUCCESS, closeable: Boolean = false, time: Long = 3000) {
        _events.add(PopupEvent(message, type, closeable, time))
    }

    fun remove(event: PopupEvent) {
        _events.remove(event)
    }

    fun removeAll(){
        _events.clear()
    }
}

// ----------------------------
// Popup Host
// ----------------------------
@Composable
fun PopupHost() {
    var currentPopup by remember { mutableStateOf<PopupEvent?>(null) }
    val events = PopupManager.events

    // Worker coroutine yang memproses popup satu per satu
    LaunchedEffect(events.size) {
        if (currentPopup == null && events.isNotEmpty()) {
            val event = events.first()
            currentPopup = event

            // Tunggu sesuai durasi popup
            delay(event.time ?: 3000L)

            // Hapus popup dan lanjut ke berikutnya
            PopupManager.remove(event)
            currentPopup = null
        }
    }

    // Tampilkan popup saat ada event aktif
    AnimatedVisibility(
        visible = currentPopup != null,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        currentPopup?.let { event ->
            val (bgColor, borderColor, iconRes) = when (event.type) {
                PopupType.SUCCESS -> Triple(Color.White, AppColors.Green500, R.drawable.ic_check_circle)
                PopupType.ERROR -> Triple(Color.White, AppColors.Red500, R.drawable.ic_error_circle)
                PopupType.WARNING -> Triple(Color.White, AppColors.Amber500, R.drawable.ic_warning_circle)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.safeDrawing.asPaddingValues()),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .background(bgColor, RoundedCornerShape(12.dp))
                        .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = borderColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = event.message,
                        fontSize = 14.sp,
                        color = borderColor
                    )
                }
            }
        }
    }
}


