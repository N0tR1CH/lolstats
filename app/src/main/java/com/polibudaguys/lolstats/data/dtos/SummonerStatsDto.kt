package com.polibudaguys.lolstats.data.dtos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polibudaguys.lolstats.data.ApiResult
import com.polibudaguys.lolstats.data.model.SummonerStatsItem
import com.polibudaguys.lolstats.data.repositories.ISummonerStatsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SummonerStatsDto(
    private val summonerStatsDto: ISummonerStatsRepository
) : ViewModel() {
    private val _summonerStats = MutableStateFlow<List<SummonerStatsItem>>(
        emptyList()
    )
    val summonerStats = _summonerStats.asStateFlow()
    private val _showErrorToastChannel = MutableStateFlow<Boolean>(false)
    val showErrorToastChannel = _showErrorToastChannel.asStateFlow()
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    fun getSummonerStats(summonerId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            summonerStatsDto.getUserStats(
                userId = summonerId
            ).collectLatest { result: ApiResult<List<SummonerStatsItem>> ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let { summonerStats: List<SummonerStatsItem> ->
                            _summonerStats.value = summonerStats
                        }
                    }
                    is ApiResult.Error -> {
                        _showErrorToastChannel.value = true
                    }
                }
                _isLoading.value = false
            }
        }
    }
}