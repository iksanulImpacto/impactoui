package com.impacto.impactoui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.library.generated.resources.Res
import com.impacto.impactoui.library.generated.resources.ic_check_circle
import org.jetbrains.compose.resources.painterResource

enum class DynamicRadioButtonPosition {
    Leading,
    Trailing
}
enum class DynamicRadioButtonType {
    RADIO,
    CHECKBOX,
    CHECKBOX_CIRCLE
}

@Composable
fun DynamicRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    imageLeading: @Composable (() -> Unit)? = null,
    imageTrailing: @Composable (() -> Unit)? = null,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    label: @Composable () -> Unit,
    labelModifier: Modifier = Modifier,
    position: DynamicRadioButtonPosition = DynamicRadioButtonPosition.Trailing,
    type: DynamicRadioButtonType = DynamicRadioButtonType.RADIO,
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(
        selectedColor = AppColors.Blue800,
        unselectedColor = AppColors.BlueGray100,
        disabledSelectedColor = AppColors.BlueGray100,
        disabledUnselectedColor = AppColors.BlueGray100
    ),
    checkBoxColor: CheckboxColors = CheckboxDefaults.colors(
        checkedColor = AppColors.Blue800,
        uncheckedColor = AppColors.BlueGray100,
        checkmarkColor = AppColors.White,
        disabledCheckedColor = AppColors.BlueGray100,
        disabledUncheckedColor = AppColors.Transparent
    ),
    circularCheckedColor: Color = AppColors.Blue800,
    circularUncheckedColor: Color = AppColors.BlueGray100,
    circularCheckmarkColor: Color = AppColors.White,
    circularDisabledColor: Color = AppColors.BlueGray100,
    onClick: () -> Unit
) {
    Row (
        modifier = modifier
            .fillMaxWidth() // Memastikan Row mengambil lebar penuh agar weight bekerja
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        imageLeading?.invoke()
        
        if (position == DynamicRadioButtonPosition.Leading) {
            DynamicSelector(
                type = type,
                selected = selected,
                enabled = enabled,
                radioButtonColors = radioButtonColors,
                checkBoxColor = checkBoxColor,
                circularCheckedColor = circularCheckedColor,
                circularUncheckedColor = circularUncheckedColor,
                circularCheckmarkColor = circularCheckmarkColor,
                circularDisabledColor = circularDisabledColor
            )
        }
        
        // Membungkus label dengan Box weight(1f) agar tidak mendorong checkbox keluar layar
        Box(
            modifier = Modifier.weight(1f).then(labelModifier),
            contentAlignment = Alignment.CenterStart
        ) {
            label()
        }
        
        if (position == DynamicRadioButtonPosition.Trailing) {
            DynamicSelector(
                type = type,
                selected = selected,
                enabled = enabled,
                radioButtonColors = radioButtonColors,
                checkBoxColor = checkBoxColor,
                circularCheckedColor = circularCheckedColor,
                circularUncheckedColor = circularUncheckedColor,
                circularCheckmarkColor = circularCheckmarkColor,
                circularDisabledColor = circularDisabledColor
            )
        }
        
        imageTrailing?.invoke()
    }
}

@Composable
private fun DynamicSelector(
    type: DynamicRadioButtonType,
    selected: Boolean,
    enabled: Boolean,
    radioButtonColors: RadioButtonColors,
    checkBoxColor: CheckboxColors,
    circularCheckedColor: Color,
    circularUncheckedColor: Color,
    circularCheckmarkColor: Color,
    circularDisabledColor: Color
) {
    when (type) {
        DynamicRadioButtonType.RADIO -> {
            RadioButton(
                selected = selected,
                onClick = null,
                enabled = enabled,
                colors = radioButtonColors
            )
        }
        DynamicRadioButtonType.CHECKBOX -> {
            Checkbox(
                checked = selected,
                onCheckedChange = null,
                enabled = enabled,
                colors = checkBoxColor
            )
        }
        DynamicRadioButtonType.CHECKBOX_CIRCLE -> {
            CircularCheckbox(
                checked = selected,
                enabled = enabled,
                checkedColor = circularCheckedColor,
                uncheckedColor = circularUncheckedColor,
                checkmarkColor = circularCheckmarkColor,
                disabledColor = circularDisabledColor
            )
        }
    }
}

@Composable
private fun CircularCheckbox(
    checked: Boolean,
    enabled: Boolean,
    checkedColor: Color,
    uncheckedColor: Color,
    checkmarkColor: Color,
    disabledColor: Color
) {
    // Ukuran 48dp untuk menyamakan dengan touch target RadioButton/Checkbox M3
    Box(
        modifier = Modifier.size(25.dp),
        contentAlignment = Alignment.Center
    ) {
        val color = if (enabled) {
            if (checked) checkedColor else uncheckedColor
        } else {
            disabledColor
        }

        if (checked) {
            // Background centang disesuaikan ke 18dp
            Box(
                modifier = Modifier
                    .size(20.dp) // Menyesuaikan dengan visual size RadioButton (20dp)
                    .clip(CircleShape)
                    .background(if (enabled) checkmarkColor else checkmarkColor.copy(alpha = 0.5f))
            )
            Icon(
                painter = painterResource(Res.drawable.ic_check_circle),
                contentDescription = null,
                modifier = Modifier.size(25.dp), // Canvas 25dp menghasilkan visual circle ~20dp
                tint = color
            )
        } else {
            // State Unchecked: Lingkaran outline saja
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(2.dp, color, CircleShape)
            )
        }
    }
}
