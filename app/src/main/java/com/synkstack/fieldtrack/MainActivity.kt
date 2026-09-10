package com.synkstack.fieldtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.synkstack.fieldtrack.ui.HomeScreen
import com.synkstack.fieldtrack.ui.theme.FieldTrackTheme

/**
 * Application entry point.
 *
 * This is an initial scaffold: it hosts a single placeholder Compose screen.
 * Feature work (location tracking, data capture, sync, etc.) is intentionally
 * not implemented yet.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FieldTrackTheme {
                HomeScreen()
            }
        }
    }
}
