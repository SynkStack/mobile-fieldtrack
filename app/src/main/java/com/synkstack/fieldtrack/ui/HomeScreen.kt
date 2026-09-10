package com.synkstack.fieldtrack.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synkstack.fieldtrack.R
import com.synkstack.fieldtrack.ui.theme.FieldTrackTheme

/**
 * Placeholder landing screen for the initial project scaffold.
 *
 * This intentionally has no navigation, state, or data dependencies yet.
 * It exists to confirm the Compose setup builds and renders end to end.
 */
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.home_title))
            Text(text = stringResource(R.string.home_subtitle))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FieldTrackTheme {
        HomeScreen()
    }
}
