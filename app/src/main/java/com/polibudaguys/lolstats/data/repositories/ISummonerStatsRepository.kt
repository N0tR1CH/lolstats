package com.polibudaguys.lolstats.data.repositories

import com.polibudaguys.lolstats.data.ApiResult
import com.polibudaguys.lolstats.data.model.SummonerStatsItem
import kotlinx.coroutines.flow.Flow

interface ISummonerStatsRepository {
    suspend fun getUserStats(userId: String): Flow<ApiResult<List<SummonerStatsItem>>>
}
