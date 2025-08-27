package com.impacto.ui

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.buttons.DynamicIconButton
import com.impacto.impactoui.forms.AppTextField
import com.impacto.ui.ui.theme.UiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    toText(innerPadding)

                }
            }
        }
    }
}

@Composable
fun toText(innerPadding: PaddingValues){

    Column(modifier = Modifier.padding(innerPadding)) {
        NormalTextFieldExample()
        PasswordFieldExample()
    }
}

@Composable
fun NormalTextFieldExample() {

    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    AppTextField(
        modifier = Modifier,
        isError = isError,
        isFocused = isFocused,
        errorText = "Field tidak boleh kosong"
    ) { innerModifier, isError ->
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = innerModifier.onFocusChanged { isFocused = it.isFocused },
            label = { Text("Nama") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )
    }

}


@Composable
fun PasswordFieldExample() {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

//    val isError = password.isEmpty()
    val errorText = "Password tidak boleh kosong"

    AppTextField(
        modifier = Modifier,
        isError = isError,
        isFocused = isFocused,
        errorText = errorText
    ) { innerModifier, isError ->
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = innerModifier.onFocusChanged { isFocused = it.isFocused },
            label = { Text("Password") },
            singleLine = true,
            isError = isError,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )
    }
}


@Composable
fun Demo() {
    val icon = painterResource(id = R.drawable.add) // your uploaded icon

    DynamicIconButton(
        icon = icon,
        imageSize = DpSize(64.dp, 64.dp),
        onClick = { /* handle click */ }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UiTheme {
        Greeting("Android")
    }
}