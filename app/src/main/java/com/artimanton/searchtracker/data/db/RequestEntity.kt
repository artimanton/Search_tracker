package com.artimanton.searchtracker.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "requests")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val queryText: String,
    val timestamp: Date,
    val url: String?
)

