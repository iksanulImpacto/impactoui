package com.impacto.impactoui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
actual fun AppAsyncImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale
) {
    val url = model as? String ?: return

    KamelImage(
        resource = { asyncPainterResource(data = url) }, // ✅ API baru: lambda composable
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        onLoading = { progress ->
            // progress: 0f..1f (nullable di versi tertentu; aman dipakai opsional)
            CircularProgressIndicator()
        },
        onFailure = { err ->
            Text("Gagal memuat gambar")
        },
        contentAlignment = Alignment.Center
    )
}