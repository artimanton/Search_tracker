package com.artimanton.searchtracker

import android.app.Application
import com.artimanton.searchtracker.data.db.RequestDatabase
import com.artimanton.searchtracker.data.repository.RequestRepository
import com.artimanton.searchtracker.viewmodel.RequestViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RequestApplication : Application(){
    lateinit var requestViewModel: RequestViewModel
    private val database by lazy { RequestDatabase.getDatabase(this) }
    private val repository by lazy { RequestRepository(database.requestDao()) }
    override fun onCreate() {
        super.onCreate()
        requestViewModel = RequestViewModel(repository.requestDao)
    }
}

