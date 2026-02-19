package com.impacto.impactoui.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors

enum class DynamicRadioButtonPosition {
    Leading,
    Trailing
}
enum class DynamicRadioButtonType {
    RADIO,
    CHECKBOX
}

@Composable
fun DynamicRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    imageLeading: @Composable (() -> Unit)? = null,
    imageTrailing: @Composable (() -> Unit)? = null,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    label: @Composable () -> Unit,
    position: DynamicRadioButtonPosition = DynamicRadioButtonPosition.Trailing,
    type: DynamicRadioButtonType = DynamicRadioButtonType.RADIO,
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(
        selectedColor = AppColors.Blue800,
        unselectedColor = AppColors.BlueGray100,
        disabledSelectedColor = AppColors.BlueGray100,
        disabledUnselectedColor = AppColors.BlueGray100
    ),
    checkBoxColor: CheckboxColors = CheckboxDefaults.colors(
        checkedColor = AppColors.Blue800, // Background biru saat checked
        uncheckedColor = AppColors.BlueGray100, // Warna border saat unchecked
        checkmarkColor = AppColors.White, // Warna centang putih saat checked
        disabledCheckedColor = AppColors.BlueGray100,
        disabledUncheckedColor = AppColors.Transparent
    ),
    onClick: () -> Unit
) {
    Row (
        modifier = modifier.clickable(
            onClick = onClick
        ),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        imageLeading?.invoke()
        
        if (position == DynamicRadioButtonPosition.Leading) {
            DynamicSelector(type, selected, radioButtonColors, checkBoxColor)
        }
        
        label()
        
        if (position == DynamicRadioButtonPosition.Trailing) {
            DynamicSelector(type, selected, radioButtonColors, checkBoxColor)
        }
        
        imageTrailing?.invoke()
    }
}

@Composable
private fun DynamicSelector(
    type: DynamicRadioButtonType,
    selected: Boolean,
    radioButtonColors: RadioButtonColors,
    checkBoxColor: CheckboxColors
) {
    when (type) {
        DynamicRadioButtonType.RADIO -> {
            RadioButton(
                selected = selected,
                onClick = null,
                colors = radioButtonColors
            )
        }
        DynamicRadioButtonType.CHECKBOX -> {
            Checkbox(
                checked = selected,
                onCheckedChange = null,
                colors = checkBoxColor
            )
        }
    }
}
