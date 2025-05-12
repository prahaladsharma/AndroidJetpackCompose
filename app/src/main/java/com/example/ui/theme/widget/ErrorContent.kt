package com.example.ui.theme.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorContent(
    message: String
) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(message) {
        snackBarHostState.showSnackbar(
            message = message, duration = SnackbarDuration.Short
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(hostState = snackBarHostState)
    }

}
