package com.artimanton.searchtracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RequestDao {
    @Query("SELECT * FROM requests")
    fun getAllRequests(): LiveData<List<RequestEntity>>

    @Insert
    suspend fun insertRequest(request: RequestEntity)

    @Delete
    suspend fun deleteRequest(request: RequestEntity)
}
