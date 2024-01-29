package com.polibudaguys.lolstats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polibudaguys.lolstats.composables.AppBottomBar
import com.polibudaguys.lolstats.composables.AppTopBar

@Preview
@Composable
fun App() {
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
            startDestination = "search",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("search") {
                Column() {
                    Text("Search")
                }
            }

            composable("history") {
                Text("History")
            }

            composable("menu") {
                Text("menu")
            }

            composable("avatar") {
                Text("avatar")
            }
        }
    }
}
