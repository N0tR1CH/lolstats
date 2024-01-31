package com.polibudaguys.lolstats.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.polibudaguys.lolstats.AppDatabase
import com.polibudaguys.lolstats.data.model.Summoner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HistoryScreen(appDatabase: AppDatabase) {
    var summoners by remember { mutableStateOf(emptyList<Summoner>()) }
    val scope = rememberCoroutineScope()
    var showSummonerStats by remember { mutableStateOf(false) }
    var currentSummonersStats: Summoner? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = Unit) {
        summoners = fetchSummoners(appDatabase)
    }

    Column() {
        Text(
            text = "History",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        summoners.forEach() { summoner ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val imageUrl =
                    "https://ddragon.leagueoflegends.com/cdn/14.2.1/img/profileicon/${summoner.profileIconId}.png"

                AsyncImage(
                    model = imageUrl,
                    contentDescription = "user avatar",
                    modifier = Modifier
                        .size(256.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .clickable(onClick = {
                            showSummonerStats = true
                            currentSummonersStats = summoner
                        })
                )

                summoner.name?.let { name ->
                    Text(
                        text = name,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }

                IconButton(onClick = {
                    scope.launch {
                        deleteSummoner(appDatabase, summoner)

                        summoners = fetchSummoners(appDatabase)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "delete summoner",
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showSummonerStats) {
            currentSummonersStats?.let { summoner ->
                SummonerStatsDialog(
                    summoner = summoner,
                    onDismiss = {
                        showSummonerStats = false
                    }
                )
            }
        }
    }
}

@Composable
fun SummonerStatsDialog(summoner: Summoner, onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Summoner Stats",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        summoner.tier?.let { findTierImage(it) }?.let { painterResource(it) }?.let {
            Image(
                painter = it,
                contentDescription = "tier image",
                Modifier
                    .size(256.dp)
                    .border(
                        10.dp,
                        MaterialTheme.colorScheme.onSurface,
                        RoundedCornerShape(50.dp),
                    )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Tier: ${summoner.tier}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Rank: ${summoner.rank}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "League Points: ${summoner.points}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Wins: ${summoner.wins}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Losses: ${summoner.losses}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onDismiss()
        }) {
            Text("Close")
        }
    }
}

suspend fun fetchSummoners(appDatabase: AppDatabase): List<Summoner> {
    return withContext(Dispatchers.IO) {
        appDatabase.summonerDao().getAll()
    }
}

suspend fun deleteSummoner(appDatabase: AppDatabase, summoner: Summoner) {
    withContext(Dispatchers.IO) {
        appDatabase.summonerDao().delete(summoner)
    }
}
