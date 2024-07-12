package com.artimanton.searchtracker

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.artimanton.searchtracker.services.BrowserRequestService
import com.artimanton.searchtracker.ui.components.QueryListScreen
import com.artimanton.searchtracker.viewmodel.RequestViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: RequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (applicationContext as RequestApplication).requestViewModel
        setContent {
            QueryListScreen(viewModel)
        }

        val accessibilityIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(accessibilityIntent)

        val serviceIntent = Intent(this, BrowserRequestService::class.java)
        startService(serviceIntent)
    }
}
