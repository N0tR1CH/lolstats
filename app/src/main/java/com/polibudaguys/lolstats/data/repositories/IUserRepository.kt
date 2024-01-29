package com.polibudaguys.lolstats.data.repositories

import com.polibudaguys.lolstats.data.ApiResult
import com.polibudaguys.lolstats.data.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUser(userName: String): Flow<ApiResult<User>>
}