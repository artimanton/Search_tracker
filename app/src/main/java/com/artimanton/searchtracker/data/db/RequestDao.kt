package com.artimanton.searchtracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface RequestDao {
    @Query("SELECT * FROM requests ORDER BY timestamp DESC")
    fun getAllRequests(): Flow<List<RequestEntity>>

    @Insert
    suspend fun insertRequest(request: RequestEntity)

    @Delete
    suspend fun deleteRequest(request: RequestEntity)
}
