package com.polibudaguys.lolstats.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
                            /*TODO*/
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
