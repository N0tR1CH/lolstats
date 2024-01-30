package com.polibudaguys.lolstats.data.repositories

import android.util.Log
import com.polibudaguys.lolstats.data.ApiResult
import com.polibudaguys.lolstats.data.model.SummonerStatsItem
import com.polibudaguys.lolstats.services.API_KEY
import com.polibudaguys.lolstats.services.RiotApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class SummonerStatsRepository(
    private val riotApiService: RiotApiService,
) : ISummonerStatsRepository {
    override suspend fun getUserStats(userId: String): Flow<ApiResult<List<SummonerStatsItem>>> {
        return flow {
            val userStats = try {
                riotApiService.fetchSummonerStats(
                    summonerId = userId,
                    apiKey = API_KEY,
                )
            } catch (exception: IOException) {
                Log.e("SummonerStatsRepository", "IOException: ${exception.message}")
                exception.printStackTrace()
                emit(ApiResult.Error(message = "Error getting user"))
                return@flow
            } catch (exception: HttpException) {
                Log.e("SummonerStatsRepository", "HttpException: ${exception.message}")
                exception.printStackTrace()
                emit(ApiResult.Error(message = "Error getting user"))
                return@flow
            } catch (exception: Exception) {
                Log.e("SummonerStatsRepository", "Exception: ${exception.message}")
                exception.printStackTrace()
                emit(ApiResult.Error(message = "Error getting user"))
                return@flow
            }
            emit(ApiResult.Success(userStats))
        }
    }
}