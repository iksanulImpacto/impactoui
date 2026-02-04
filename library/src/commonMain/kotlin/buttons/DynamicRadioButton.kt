package com.impacto.impactoui.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors

enum class DynamicRadioButtonPosition {
    Leading,
    Trailing
}

@Composable
fun DynamicRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    imageLeading: @Composable (() -> Unit)?,
    imageTrailing: @Composable (() -> Unit)?,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    label: @Composable () -> Unit,
    position: DynamicRadioButtonPosition = DynamicRadioButtonPosition.Trailing,
    colors: RadioButtonColors = RadioButtonColors(
        selectedColor = AppColors.Blue800,
        unselectedColor = AppColors.BlueGray100,
        disabledSelectedColor = AppColors.BlueGray100,
        disabledUnselectedColor = AppColors.BlueGray100
    ),
    onClick: () -> Unit
) {
    Row (
        modifier = modifier.clickable(
            true,
            onClick = onClick
        ),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        imageLeading?.invoke()
        if (position == DynamicRadioButtonPosition.Leading) {
            RadioButton(
                selected = selected,
                onClick = null,
                colors = colors
            )
        }
        label()
        if (position == DynamicRadioButtonPosition.Trailing) {
            RadioButton(
                selected = selected,
                onClick = null,
                colors = colors
            )
        }
        imageTrailing?.invoke()
    }
}
