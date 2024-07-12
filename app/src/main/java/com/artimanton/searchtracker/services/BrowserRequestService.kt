package com.artimanton.searchtracker.services

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.artimanton.searchtracker.RequestApplication
import com.artimanton.searchtracker.data.db.RequestEntity
import com.artimanton.searchtracker.viewmodel.RequestViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Date

class BrowserRequestService() : AccessibilityService() {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    private lateinit var viewModel: RequestViewModel

    override fun onCreate() {
        super.onCreate()
        val application = applicationContext as RequestApplication
        viewModel = application.requestViewModel
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            val eventType = event.eventType
            val source = event.source
            val packageName = event.packageName?.toString()
            val className = event.className?.toString()
            val text = source?.text?.toString()

            Log.d("MyLog", "Accessibility event received. Type: $eventType, Package: $packageName, Class: $className, Text: $text")

            if (text?.contains("google.com", ignoreCase = true) == true) {
                val request = RequestEntity(
                    queryText = text,
                    timestamp = Date(),
                    url = packageName // You may want to verify if packageName is indeed the URL
                )


                serviceScope.launch {
                    viewModel.insert(request)
                }
            }
        }
    }

    override fun onInterrupt() {
        // Handle interrupt
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}

