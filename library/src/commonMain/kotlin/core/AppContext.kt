package com.impacto.impactoui.core

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object AppContext {
    lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }
    fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun hasPermissions(context: Context, permissions: List<String>): Boolean {
        return permissions.all { hasPermission(context, it) }
    }

    fun requestPermission(activity: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }

    fun requestPermissions(activity: Activity, permissions: List<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)
    }
}