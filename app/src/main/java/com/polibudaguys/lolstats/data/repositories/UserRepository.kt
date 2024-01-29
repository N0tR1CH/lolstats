package com.polibudaguys.lolstats.data.repositories

import com.polibudaguys.lolstats.data.ApiResult
import com.polibudaguys.lolstats.data.model.User
import com.polibudaguys.lolstats.services.RiotApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class UserRepository(
    private val riotApiService: RiotApiService,
): IUserRepository {
    override suspend fun getUser(userName: String): Flow<ApiResult<User>> {
        return flow {
            val user = try {
                riotApiService.fetchSummonerByName(
                    name = userName,
                    apiKey = "RGAPI-92ae25fb-00bb-4bc0-9f63-9a66613e6078",
                )
            } catch (exception: IOException) {
                exception.printStackTrace()
                emit(ApiResult.Error(message = "Error getting user"))
                return@flow
            } catch (exception: HttpException) {
                exception.printStackTrace()
                emit(ApiResult.Error(message = "Error getting user"))
                return@flow
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(ApiResult.Error(message = "Error getting user"))
                return@flow
            }
            emit(ApiResult.Success(user))
        }
    }
}