package com.artimanton.searchtracker.di

import android.app.Application
import androidx.room.Room
import com.artimanton.searchtracker.data.db.RequestDao
import com.artimanton.searchtracker.data.db.RequestDatabase
import com.artimanton.searchtracker.data.repository.RequestRepository
import com.artimanton.searchtracker.utils.Constants.DB_NAME
import com.artimanton.searchtracker.viewmodel.RequestViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RequestDatabase {
        return Room.databaseBuilder(app, RequestDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideQueryDao(db: RequestDatabase): RequestDao {
        return db.requestDao()
    }

    @Provides
    @Singleton
    fun provideRepository(queryDao: RequestDao): RequestRepository {
        return RequestRepository(queryDao)
    }

    @Provides
    @Singleton
    fun provideSharedViewModel(repository: RequestRepository): RequestViewModel {
        return RequestViewModel(repository.requestDao)
    }
}
