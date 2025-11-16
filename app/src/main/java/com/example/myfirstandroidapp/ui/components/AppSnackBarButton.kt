package com.example.myfirstandroidapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfirstandroidapp.ui.theme.MyFirstAndroidAppTheme
import kotlinx.coroutines.launch

@Composable
fun AppSnackBarButton(
    snackbarHostState: SnackbarHostState,
    label: String,
    message: String,
    actionLabel: String ? = null
) {
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
            }
        }
    ) {
        Text(label)
    }
}


@Preview(showBackground = true)
@Composable
fun AppSnackBarButtonPreview() {
    MyFirstAndroidAppTheme {

    }
}