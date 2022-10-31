package com.android.pagingremotemediator.di

import com.android.pagingremotemediator.data.retrofit.LaunchesApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

   @Provides
   @Singleton
   fun provideLaunchesApi(
      retrofit: Retrofit
   ): LaunchesApi {
      return retrofit.create(LaunchesApi::class.java)
   }

   @Provides
   @Singleton
   fun provideRetrofit(
      okHttpClient: OkHttpClient,
      gson: Gson
   ): Retrofit {
      return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .client(okHttpClient)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
   }

   @Provides
   @Singleton
   fun provideOkHttpClient(): OkHttpClient {
      return OkHttpClient.Builder()
         .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
         .build()
   }

   @Provides
   @Singleton
   fun provideGson(): Gson {
      return GsonBuilder()
         .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
         .create()
   }

   private companion object {
      const val BASE_URL = "https://api.spacexdata.com/v4/"
   }
}