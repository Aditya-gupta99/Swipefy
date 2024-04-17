package com.sparklead.swipefy.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sparklead.swipefy.app.SwipefyApp
import com.sparklead.swipefy.presentation.exoplayer.MusicService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isServiceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipefyApp {
                startService()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MusicService::class.java))
        isServiceRunning = false
    }

    private fun startService() {
        if (!isServiceRunning) {
            val intent = Intent(this, MusicService::class.java)
            startForegroundService(intent)
            isServiceRunning = true
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwipefyApp {

    }
}