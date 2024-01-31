package com.polibudaguys.lolstats

import android.os.Bundle
import android.os.CountDownTimer
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
import com.polibudaguys.lolstats.utils.NotificationUtils

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
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Do nothing
            }

            override fun onFinish() {
                NotificationUtils.showNotification(
                    applicationContext,
                    "Hey",
                    "Check out how your friends are doing!",
                )
            }
        }.start()

        val db: AppDatabase = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "lol-stats-db"
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
