package com.impacto.impactoui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors

@Composable
fun <T> GenericList(
    list: List<T>,
    key: ((T) -> Any)? = null,
    onItemClick: (T) -> Unit = {},
    modifier: Modifier = Modifier,
    rowContent: @Composable (T) -> Unit
) {
    Box(modifier) {
        LazyColumn {
            items(
                items = list,
                key = key
            ) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    rowContent(item)
                }
            }
            item {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = DividerDefaults.Thickness,
                    color = AppColors.Grey50
                )
            }
        }
    }
}