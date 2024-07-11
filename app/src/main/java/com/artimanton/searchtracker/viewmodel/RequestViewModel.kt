package com.artimanton.searchtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artimanton.searchtracker.data.db.RequestDao
import com.artimanton.searchtracker.data.db.RequestEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val requestDao: RequestDao
) : ViewModel() {
    val queries = requestDao.getAllRequests()

    fun insert(queries: RequestEntity) {
        viewModelScope.launch {
            requestDao.insertRequest(queries)
        }
    }

    fun deleteQuery(queries: RequestEntity) {
        viewModelScope.launch {
            requestDao.deleteRequest(queries)
        }
    }
}

