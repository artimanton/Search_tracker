package com.artimanton.searchtracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.artimanton.searchtracker.utils.Constants.DB_NAME
import com.artimanton.searchtracker.utils.DateTypeConverter

@Database(entities = [RequestEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class RequestDatabase : RoomDatabase() {
    abstract fun requestDao(): RequestDao

    companion object {
        @Volatile
        private var INSTANCE: RequestDatabase? = null

        fun getDatabase(context: Context): RequestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RequestDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}