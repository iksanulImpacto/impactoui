package com.impacto.impactoui.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.library.generated.resources.Res
import com.impacto.impactoui.library.generated.resources.ic_check_circle
import com.impacto.impactoui.library.generated.resources.ic_warning_circle

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImpactoButtonAndroidPreview() {
    var selectedType by remember { mutableStateOf(ImpactoButtonType.Filled) }
    val types = ImpactoButtonType.entries

    MaterialTheme {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp
                ) {
                    types.forEach { type ->
                        NavigationBarItem(
                            selected = selectedType == type,
                            onClick = { selectedType = type },
                            label = { Text(type.name, style = MaterialTheme.typography.labelSmall) },
                            icon = {
                                Icon(
                                    imageVector = when (type) {
                                        ImpactoButtonType.Filled -> Icons.Default.CheckCircle
                                        ImpactoButtonType.Elevated -> Icons.Default.Menu
                                        ImpactoButtonType.Tonal -> Icons.Default.Settings
                                        ImpactoButtonType.Outlined -> Icons.Default.Build
                                        ImpactoButtonType.Text -> Icons.Default.Info
                                    },
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = AppColors.Grey50
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "${selectedType.name} Style Variations",
                        style = MaterialTheme.typography.headlineMedium,
                        color = AppColors.ImpactoPrimary
                    )

                    // Section 1: Sizes
                    PreviewSection(title = "Button Sizes") {
                        ImpactoButtonSize.entries.forEach { size ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = size.name,
                                    modifier = Modifier.width(32.dp),
                                    style = MaterialTheme.typography.bodySmall
                                )
                                ImpactoButton(
                                    text = "Button ${size.name}",
                                    buttonSize = size,
                                    type = selectedType,
                                    onClick = {}
                                )
                                ImpactoButton(
                                    text = "Icon ${size.name}",
                                    buttonSize = size,
                                    type = selectedType,
                                    icon = Res.drawable.ic_check_circle,
                                    onClick = {}
                                )
                            }
                        }
                    }

                    // Section 2: Icons
                    PreviewSection(title = "Icons & Loading") {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ImpactoButton(
                                text = "Leading Icon",
                                type = selectedType,
                                icon = Res.drawable.ic_check_circle,
                                onClick = {},
                                modifier = Modifier.weight(1f)
                            )
                            ImpactoButton(
                                text = "Trailing",
                                type = selectedType,
                                icon = Res.drawable.ic_warning_circle,
                                iconPosition = ImpactoIconPosition.Trailing,
                                onClick = {},
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ImpactoButton(
                                text = "Loading State",
                                type = selectedType,
                                isLoading = true,
                                onClick = {},
                                modifier = Modifier.weight(1f)
                            )
                            ImpactoButton(
                                text = "Disabled",
                                type = selectedType,
                                enabled = false,
                                onClick = {},
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    // Section 3: Shapes
                    PreviewSection(title = "Custom Shapes") {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ImpactoButton(
                                text = "Rectangle",
                                type = selectedType,
                                shape = RectangleShape,
                                onClick = {},
                                modifier = Modifier.weight(1f)
                            )
                            ImpactoButton(
                                text = "Rounded Full",
                                type = selectedType,
                                shape = CircleShape,
                                onClick = {},
                                modifier = Modifier.weight(1f)
                            )
                        }
                        ImpactoButton(
                            text = "Custom 24dp Radius",
                            type = selectedType,
                            shape = RoundedCornerShape(24.dp),
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // Section 4: Gradients & Shadows (Most visible on Filled)
                    if (selectedType == ImpactoButtonType.Filled || selectedType == ImpactoButtonType.Outlined) {
                        PreviewSection(title = "Gradients & Shadows") {
                            ImpactoButton(
                                text = "Horizontal Gradient Blue",
                                type = selectedType,
                                backgroundBrush = if (selectedType == ImpactoButtonType.Filled) Brush.horizontalGradient(
                                    colors = listOf(AppColors.Blue500, AppColors.Blue800)
                                ) else null,
                                borderBrush = if (selectedType == ImpactoButtonType.Outlined) Brush.horizontalGradient(
                                    colors = listOf(AppColors.Blue500, AppColors.Blue800)
                                ) else null,
                                showInnerShadow = selectedType == ImpactoButtonType.Filled,
                                onClick = {},
                                modifier = Modifier.fillMaxWidth()
                            )
                            
                            ImpactoButton(
                                text = "Inner Shadow Only",
                                type = selectedType,
                                showInnerShadow = true,
                                onClick = {},
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    // Section 5: Branding Colors
                    PreviewSection(title = "Branding Colors") {
                        val brandingColors = listOf(
                            "Blue" to AppColors.Blue500,
                            "Green" to AppColors.Green500,
                            "Red" to AppColors.Red500,
                            "Amber" to AppColors.Amber500
                        )

                        brandingColors.chunked(2).forEach { pair ->
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                pair.forEach { (name, color) ->
                                    ImpactoButton(
                                        text = name,
                                        type = selectedType,
                                        colors = when (selectedType) {
                                            ImpactoButtonType.Filled -> ButtonDefaults.buttonColors(containerColor = color)
                                            ImpactoButtonType.Outlined -> ButtonDefaults.outlinedButtonColors(contentColor = color)
                                            ImpactoButtonType.Text -> ButtonDefaults.textButtonColors(contentColor = color)
                                            else -> ButtonDefaults.buttonColors(containerColor = color)
                                        },
                                        onClick = {},
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
            }
        }
    }
}

@Composable
private fun PreviewSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
        HorizontalDivider(color = AppColors.Grey300)
        content()
    }
}
