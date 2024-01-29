package com.polibudaguys.lolstats.services

import com.polibudaguys.lolstats.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApiService {
    @GET("summoner/v4/summoners/by-name/{name}")
    suspend fun fetchSummonerByName(
        @Path("name") name: String,
        @Query("api_key") apiKey: String,
    ): User

    companion object {
        const val BASE_URL = "https://eun1.api.riotgames.com/lol/"
    }
}