package com.impacto.impactoui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// ----------------------------
// Event & State
// ----------------------------
enum class PopupState { SUCCESS, WARNING, ERROR }

data class PopupEvent(
    val message: String,
    val type: PopupState,
    val content: (@Composable () -> Unit)? = null // slot API
)

// ----------------------------
// Singleton Manager
// ----------------------------
object PopupWarning {

    private val _eventFlow = MutableSharedFlow<PopupEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    /** Simple API: message + type */
    fun show(message: String, type: PopupState = PopupState.SUCCESS, scope: CoroutineScope) {
        scope.launch { _eventFlow.emit(PopupEvent(message, type)) }
    }

    /** Slot API: custom composable */
    fun showCustom(type: PopupState, scope: CoroutineScope, content: @Composable () -> Unit) {
        scope.launch { _eventFlow.emit(PopupEvent(message = "", type = type, content = content)) }
    }
}

// ----------------------------
// Composable Listener
// ----------------------------
@Composable
fun PopupWarningHost(scope: CoroutineScope) {
    val hostState = remember { SnackbarHostState() }

    // Keep track of last event
    var lastEvent by remember { mutableStateOf<PopupEvent?>(null) }

    // Listen to events
    LaunchedEffect(Unit) {
        PopupWarning.eventFlow.collect { event ->
            lastEvent = event
            scope.launch {
                if (event.content == null) {
                    hostState.showSnackbar(message = event.message)
                } else {
                    hostState.showSnackbar(message = "") // trigger recomposition
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        SnackbarHost(hostState = hostState) { _ ->
            // Custom slot API
            lastEvent?.content?.invoke() ?: run {
                lastEvent?.let { event ->
                    val bgColor = when (event.type) {
                        PopupState.SUCCESS -> AppColors.Green500
                        PopupState.WARNING -> AppColors.Amber500
                        PopupState.ERROR -> AppColors.Red500
                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(bgColor, RoundedCornerShape(12.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(text = event.message, color = Color.White)
                    }
                }
            }
        }
    }
}

