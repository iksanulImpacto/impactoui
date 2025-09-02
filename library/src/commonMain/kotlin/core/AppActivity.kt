package com.impacto.impactoui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
/**
 * Base activity untuk Compose apps
 */
abstract class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun RequestPermission(
        permission: String,
        onResult: (Boolean) -> Unit
    ) {
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted -> onResult(granted) }

        LaunchedEffect(permission) {
            if (!AppContext.hasPermission(context, permission) && context is Activity) {
                launcher.launch(permission)
            } else {
                onResult(true)
            }
        }
    }

    @Composable
    fun RequestPermissions(
        permissions: List<String>,
        requireAll: Boolean = true,
        onResult: (Boolean, Map<String, Boolean>) -> Unit
    ) {
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { results ->
            val allGranted = if (requireAll) results.values.all { it } else results.values.any { it }
            onResult(allGranted, results)
        }

        LaunchedEffect(permissions) {
            if (!AppContext.hasPermissions(context, permissions) && context is Activity) {
                launcher.launch(permissions.toTypedArray())
            } else {
                val grantedMap = permissions.associateWith { AppContext.hasPermission(context, it) }
                val allGranted = if (requireAll) grantedMap.values.all { it } else grantedMap.values.any { it }
                onResult(allGranted, grantedMap)
            }
        }
    }
}