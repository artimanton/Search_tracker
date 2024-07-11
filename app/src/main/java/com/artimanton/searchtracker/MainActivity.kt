package com.artimanton.searchtracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.artimanton.searchtracker.services.BrowserRequestService
import com.artimanton.searchtracker.ui.components.QueryListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QueryListScreen()
        }

        // Start the accessibility service
        val intent = Intent(this, BrowserRequestService::class.java)
        startService(intent)
    }
}
