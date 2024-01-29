package com.polibudaguys.lolstats

import com.polibudaguys.lolstats.services.RiotApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val api: RiotApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(RiotApiService.BASE_URL)
        .client(client)
        .build()
        .create(RiotApiService::class.java)
}