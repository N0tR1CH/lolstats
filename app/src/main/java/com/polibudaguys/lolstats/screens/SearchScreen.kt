package com.polibudaguys.lolstats.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.polibudaguys.lolstats.R
import com.polibudaguys.lolstats.data.SummonerStatsDto
import com.polibudaguys.lolstats.data.UserDto
import com.polibudaguys.lolstats.data.model.SummonerStatsItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    userViewModel: UserDto,
    summonerStatsViewModel: SummonerStatsDto,
) {
    var summonerName by remember { mutableStateOf("") }
    val user by userViewModel.user.collectAsState()
    val summonerStats by summonerStatsViewModel.summonerStats.collectAsState()
    val context = LocalContext.current
    val isUserLoading by userViewModel.isLoading.collectAsState()
    val imageUrl =
        "https://ddragon.leagueoflegends.com/cdn/14.2.1/img/profileicon/${user.profileIconId}.png"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to LoL Stats",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = summonerName,
            onValueChange = { summonerName = it },
            label = { Text("Enter Summoner's Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (summonerName.isEmpty()) {
                Toast.makeText(
                    context, "summoner name cant be empty", Toast.LENGTH_SHORT
                ).show()
            } else {
                userViewModel.getUser(summonerName)
            }
        }) {
            Text("Search")
        }

        if (isUserLoading) {
            CircularProgressIndicator()
        } else {
            if (user.profileIconId != 0) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "user avatar",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(128.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
            }

            if (user.summonerLevel != 0) {
                Text(
                    text = "Summoner Level: ${user.summonerLevel}",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(modifier = Modifier.padding(16.dp), onClick = {
                    summonerStatsViewModel.getSummonerStats(user.id)
                }) {
                    Text("Do you want to learn more about ${user.name}?")
                }
            }
        }

        if (summonerStats.isNotEmpty()) {
            summonerStats.forEach() { stats ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    SummonerStats(summonerStatsItem = stats)
                }
            }
        }
    }

    LaunchedEffect(key1 = userViewModel.showErrorToastChannel) {
        userViewModel.showErrorToastChannel.collectLatest { show ->
            if (show) {
                Toast.makeText(
                    context, "Error", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    LaunchedEffect(key1 = summonerStatsViewModel.showErrorToastChannel) {
        summonerStatsViewModel.showErrorToastChannel.collectLatest { show ->
            if (show) {
                Toast.makeText(
                    context, "Error", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun SummonerStats(summonerStatsItem: SummonerStatsItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Summoner Stats",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Image(
            painter = painterResource(findTierImage(summonerStatsItem.tier)),
            contentDescription = "tier image",
            Modifier
                .size(256.dp)
                .border(
                    10.dp,
                    MaterialTheme.colorScheme.onSurface,
                    RoundedCornerShape(50.dp),
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Tier: ${summonerStatsItem.tier}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Rank: ${summonerStatsItem.rank}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "League Points: ${summonerStatsItem.leaguePoints}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Wins: ${summonerStatsItem.wins}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Losses: ${summonerStatsItem.losses}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    }
}

fun findTierImage(tier: String): Int {
    val image = when (tier) {
        "BRONZE" -> TierImage.bronze
        "CHALLENGER" -> TierImage.challenger
        "DIAMOND" -> TierImage.diamond
        "EMERALD" -> TierImage.emerald
        "GOLD" -> TierImage.gold
        "GRANDMASTER" -> TierImage.grandmaster
        "IRON" -> TierImage.iron
        "MASTER" -> TierImage.master
        "PLATINUM" -> TierImage.platinum
        "SILVER" -> TierImage.silver
        else -> R.drawable.ic_launcher_background
    }

    return image
}

object TierImage {
    val bronze = R.drawable.rank_bronze
    val challenger = R.drawable.rank_challenger
    val diamond = R.drawable.rank_diamond
    val emerald = R.drawable.rank_emerald
    val gold = R.drawable.rank_gold
    val grandmaster = R.drawable.rank_grandmaster
    val iron = R.drawable.rank_iron
    val master = R.drawable.rank_master
    val platinum = R.drawable.rank_platinum
    val silver = R.drawable.rank_silver
}