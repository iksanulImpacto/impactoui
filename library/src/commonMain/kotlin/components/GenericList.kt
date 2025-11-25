package com.impacto.impactoui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle

@Composable
fun <T> GenericList(
    list: List<T>,
    key: ((T) -> Any)? = null,
    onItemClick: (T) -> Unit = {},
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(0.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    modifierDivider: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    withDivider: Boolean = true,
    rowContent: @Composable (T) -> Unit,
) {
    LazyColumn (
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        state = state,
        modifier = modifier
    ) {
        items(list, key = key) { item ->
            ListItem(
                headlineContent = {
                    rowContent(item)
                },
                modifier = Modifier.clickable {
                    onItemClick(item)
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
            if (withDivider) {
                HorizontalDivider(modifier = modifierDivider)
            }
        }
    }
}