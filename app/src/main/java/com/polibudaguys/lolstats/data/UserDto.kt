package com.polibudaguys.lolstats.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polibudaguys.lolstats.data.model.User
import com.polibudaguys.lolstats.data.repositories.IUserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserDto(
    private val userRepository: IUserRepository
): ViewModel() {
    private val _user: MutableStateFlow<User> = MutableStateFlow<User>(
        User(
            accountId = "",
            id = "",
            name = "",
            profileIconId = 0,
            puuid = "",
            revisionDate = 0,
            summonerLevel = 0,
        )
    )
    private val _showErrorToastChannel: Channel<Boolean> = Channel<Boolean>()
    val user: StateFlow<User> = _user.asStateFlow()
    val showErrorToastChannel: Flow<Boolean> = _showErrorToastChannel.receiveAsFlow()
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getUser(userName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            userRepository.getUser(
                userName = userName
            ).collectLatest { result: ApiResult<User> ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let { user: User ->
                            _user.value = user
                        }
                    }

                    is ApiResult.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                }
                _isLoading.value = false
            }
        }
    }
}