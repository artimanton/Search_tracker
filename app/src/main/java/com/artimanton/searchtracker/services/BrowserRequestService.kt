package com.artimanton.searchtracker.services

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.artimanton.searchtracker.RequestApplication
import com.artimanton.searchtracker.data.db.RequestEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class BrowserRequestService : AccessibilityService() {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            val source = it.source
            val text = source?.text?.toString() ?: return

            if (text.contains("google.com")) {
                val url = it.packageName?.toString()
                val request = RequestEntity(
                    queryText = text,
                    timestamp = Date(),
                    url = url
                )
                val repository = (application as RequestApplication).repository

                serviceScope.launch {
                    repository?.insert(request)
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

