package com.impacto.impactoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors


@Composable
fun <T: Enum<T>> StepperIndicator(
    modifier: Modifier = Modifier,
    steps: Array<T>,
    currentStep: T,
    completeColor: Color = AppColors.BillId.Blue300,
    unCompleteColor: Color = AppColors.BillId.Blue100
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        steps.forEachIndexed { index, _ ->
            val isCompleted = currentStep.ordinal >= index
            // Tampilan untuk setiap node langkah
            StepNode(
                modifier = Modifier.weight(1f),
                isCompleted = isCompleted,
                completeColor = completeColor,
                unCompleteColor = unCompleteColor
            )
        }
    }
}

@Composable
fun StepNode(
    modifier: Modifier,
    isCompleted: Boolean,
    completeColor: Color,
    unCompleteColor: Color
) {
    Box(
        modifier = modifier
            .height(8.dp)
            .background(
                color = when {
                    isCompleted -> completeColor
                    else -> unCompleteColor
                },
                shape = RoundedCornerShape(4.dp)
            )
    )
}