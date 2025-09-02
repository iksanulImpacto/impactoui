package com.impacto.impactoui.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

object FileUtil {
    private const val TAG = "FilePathUtil"

    fun getUriFromFile(context: Context, file: File): Uri {
        val applicationId = "${context.packageName}.fileprovider"
        return FileProvider.getUriForFile(context, applicationId, file)
    }

    fun getFileNameFromPath(path: String): String {
        return path.substring(path.lastIndexOf("/") + 1)
    }

    fun isValidURL(url: String?): Boolean {
        return try {
            URL(url).toURI()
            true
        } catch (_: MalformedURLException) {
            false
        } catch (_: URISyntaxException) {
            false
        }
    }

    fun createTemporaryFile(root: File, extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return File.createTempFile("TEMP_$timeStamp", extension, root).apply {
            Log.i(TAG, "createTemporaryFile: $absolutePath")
            if (!exists()) mkdirs()
        }
    }

    fun isValidFile(filePath: String): Boolean {
        return File(filePath).isFile
    }

    fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
        return try {
            val parcelContentDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
            val fileDescriptor = parcelContentDescriptor?.fileDescriptor
            val inputStream = FileInputStream(fileDescriptor)
            val outputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var read: Int
            while (inputStream.read(buffer).also { read = it } > 0) {
                outputStream.write(buffer, 0, read)
            }
            inputStream.close()
            outputStream.close()
            outputStream.toByteArray()
        } catch (e: IOException) {
            Log.e(TAG, "Error when convert Uri to ByteArray", e)
            null
        }
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    fun getMimeType(fullPath: String): String? {
        val extension = fullPath.substring(fullPath.lastIndexOf(".") + 1)
        Log.i(TAG, "MimeType Generator. Extension: $extension")
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)?.also {
            Log.i(TAG, "MimeType Generator. Mime type: $it")
        }
    }

    fun getFilePath(context: Context, uri: Uri): String? {
        Log.i(TAG, "plain text: $uri")
        Log.i(TAG, "authority: ${uri.authority}")

        if (DocumentsContract.isDocumentUri(context, uri)) {
            when {
                isExternalStorageDocument(uri) -> {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":")
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }
                }
                isDownloadsDocument(uri) -> {
                    val id = DocumentsContract.getDocumentId(uri)
                    val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        id.toLong()
                    )
                    return getDataColumn(context, contentUri, null, null)
                }
                isMediaDocument(uri) -> {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":")
                    val type = split[0]
                    val contentUri: Uri? = when (type) {
                        "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                        "audio" -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        else -> null
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(split[1])
                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment
            else getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    private fun getDataColumn(
        context: Context,
        uri: Uri?,
        selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri) =
        "com.android.externalstorage.documents" == uri.authority

    private fun isDownloadsDocument(uri: Uri) =
        "com.android.providers.downloads.documents" == uri.authority

    private fun isMediaDocument(uri: Uri) =
        "com.android.providers.media.documents" == uri.authority

    private fun isGooglePhotosUri(uri: Uri) =
        "com.google.android.apps.photos.content" == uri.authority
}

/**
 * Convenience untuk dipakai di Compose tanpa manual `LocalContext.current`
 */
@Composable
fun rememberFileUtilContext(): Context {
    return LocalContext.current
}
