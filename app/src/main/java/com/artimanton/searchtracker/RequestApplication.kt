package com.artimanton.searchtracker

import android.app.Application
import com.artimanton.searchtracker.data.db.RequestDatabase
import com.artimanton.searchtracker.data.repository.RequestRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RequestApplication : Application(){
    private val database by lazy { RequestDatabase.getDatabase(this) }
    val repository by lazy { database.requestDao()?.let { RequestRepository(it) } }
}

