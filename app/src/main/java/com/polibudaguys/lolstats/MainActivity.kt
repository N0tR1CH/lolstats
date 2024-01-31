package com.polibudaguys.lolstats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.polibudaguys.lolstats.data.dtos.SummonerStatsDto
import com.polibudaguys.lolstats.data.dtos.UserDto
import com.polibudaguys.lolstats.data.repositories.SummonerStatsRepository
import com.polibudaguys.lolstats.data.repositories.UserRepository
import com.polibudaguys.lolstats.ui.theme.LOLStatsTheme

class MainActivity : ComponentActivity() {
    private val userViewModel: UserDto by viewModels<UserDto>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserDto(UserRepository(RetrofitInstance.api)) as T
            }
        }
    })

    private val summonerStatsViewModel: SummonerStatsDto by viewModels<SummonerStatsDto>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SummonerStatsDto(SummonerStatsRepository(RetrofitInstance.api)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db: AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "lol-stats-db"
        ).build()

        setContent {
            LOLStatsTheme {
                App(
                    userViewModel = userViewModel,
                    summonerStatsViewModel = summonerStatsViewModel,
                    appDatabase = db,
                )
            }
        }
    }
}
