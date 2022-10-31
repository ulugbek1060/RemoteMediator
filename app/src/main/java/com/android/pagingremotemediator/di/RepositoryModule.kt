package com.android.pagingremotemediator.di

import androidx.paging.ExperimentalPagingApi
import com.android.pagingremotemediator.data.DefaultLaunchesRepository
import com.android.pagingremotemediator.domain.LaunchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

   @OptIn(ExperimentalPagingApi::class)
   @Binds
   fun bindLunchesRepository(
      defaultLaunchesRepository: DefaultLaunchesRepository
   ): LaunchesRepository
}