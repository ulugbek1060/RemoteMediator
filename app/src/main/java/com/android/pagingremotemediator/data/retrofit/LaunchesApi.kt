package com.android.pagingremotemediator.data.retrofit

import retrofit2.http.Body
import retrofit2.http.POST


/**
 * API for fetching SpaceX Launches.
 */
interface LaunchesApi {

   /**
    * Note that orderBy and ASC/DESC order should be the same as in the database query!
    */
   @POST("launches/query")
   suspend fun getLaunches(
      @Body launchesQuery: LaunchesQuery
   ): LaunchesResponse
}