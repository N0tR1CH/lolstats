package com.polibudaguys.lolstats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polibudaguys.lolstats.composables.AppBottomBar
import com.polibudaguys.lolstats.composables.AppTopBar
import com.polibudaguys.lolstats.constants.Routes
import com.polibudaguys.lolstats.data.SummonerStatsDto
import com.polibudaguys.lolstats.data.UserDto
import com.polibudaguys.lolstats.screens.AvatarScreen
import com.polibudaguys.lolstats.screens.SearchScreen

@Composable
fun App(
    userViewModel: UserDto,
    summonerStatsViewModel: SummonerStatsDto,
) {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar(navController = navController)
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        },
    ) { innerPadding: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.Search,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Routes.Search) {
                Column {
                    SearchScreen(
                        userViewModel = userViewModel,
                        summonerStatsViewModel = summonerStatsViewModel,
                    )
                }
            }

            composable(Routes.History) {
                Text("History")
            }

            composable(Routes.Menu) {
                Text("menu")
            }

            composable(Routes.Avatar) {
                AvatarScreen(userViewModel)
            }
        }
    }
}
