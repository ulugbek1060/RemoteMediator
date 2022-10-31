package com.android.pagingremotemediator.di

import android.content.Context
import androidx.room.Room
import com.android.pagingremotemediator.data.room.AppDatabase
import com.android.pagingremotemediator.data.room.LaunchesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

   @Singleton
   @Provides
   fun provideDatabase(
      @ApplicationContext context: Context
   ): AppDatabase {
      return Room
         .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
         .build()
   }

   @Singleton
   @Provides
   fun provideLaunchDao(appDatabase: AppDatabase): LaunchesDao {
      return appDatabase.getLaunchesDao()
   }

   private companion object {
      const val DB_NAME = "launches.db"
   }
}