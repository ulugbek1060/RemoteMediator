package com.android.pagingremotemediator.data.retrofit

import com.android.pagingremotemediator.domain.Launch
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

data class LaunchNetworkEntity(
   @SerializedName("flight_number") override val id: Long,
   override val name: String,
   val details: String?,
   val links: Links?,
   val dateUnix: Long,
   val success: Boolean
) : Launch {

   override val description: String get() = details ?: "-"
   override val imageUrl: String get() = links?.patch?.small ?: ""
   override val year: Int
      get() = Calendar.getInstance()
         .apply {
            timeInMillis = TimeUnit.SECONDS.toMillis(launchTimestamp)
         }.get(Calendar.YEAR)
   override val launchTimestamp: Long get() = dateUnix
   override val isSuccess: Boolean get() = success

}

data class Links(
   val patch: Images?,
)

data class Images(
   val small: String?
)
