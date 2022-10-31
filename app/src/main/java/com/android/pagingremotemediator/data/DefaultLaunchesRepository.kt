package com.android.pagingremotemediator.data

import androidx.paging.*
import com.android.pagingremotemediator.data.room.LaunchRoomEntity
import com.android.pagingremotemediator.data.room.LaunchesDao
import com.android.pagingremotemediator.domain.Launch
import com.android.pagingremotemediator.domain.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [LaunchesRepository] which uses
 * [LaunchesDao] and [LaunchesRemoteMediator] as data sources.
 */
@ExperimentalPagingApi
@Singleton
class DefaultLaunchesRepository @Inject constructor(
   private val launchesDao: LaunchesDao,
   private val remoteMediatorFactory: LaunchesRemoteMediator.Factory
) : LaunchesRepository {
   override fun getLaunches(year: Int?): Flow<PagingData<Launch>> {
      return Pager(
         config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE // not required, may be deleted
         ),
         remoteMediator = remoteMediatorFactory.create(year = year),
         pagingSourceFactory = { launchesDao.getPagingSource(year) }
      )
         .flow
         //.map { it as PagingData<Launch> }
         .map { pagingData ->
            pagingData.map { launchRoomEntity ->
               launchRoomEntity
            }
         }
   }

   override suspend fun toggleSuccessFlag(launch: Launch) {

      // todo: call an endpoint here for editing the Launch if such endpoint exists :)

      val editedEntity = LaunchRoomEntity(launch)
         .copy(isSuccess = !launch.isSuccess)
      launchesDao.save(editedEntity)
   }

   private companion object {
      const val PAGE_SIZE = 30
   }
}