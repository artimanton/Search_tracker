package com.artimanton.searchtracker.data.repository

import com.artimanton.searchtracker.data.db.RequestDao
import com.artimanton.searchtracker.data.db.RequestEntity
import javax.inject.Inject

class RequestRepository @Inject constructor(private val requestDao: RequestDao) {

    fun getAllQueries() = requestDao.getAllRequests()

    suspend fun insert(entity: RequestEntity) {
        requestDao.insertRequest(entity)
    }

    suspend fun delete(entity: RequestEntity) {
        requestDao.deleteRequest(entity)
    }
}

