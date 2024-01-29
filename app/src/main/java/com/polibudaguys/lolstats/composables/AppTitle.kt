package com.polibudaguys.lolstats.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polibudaguys.lolstats.R

@Preview
@Composable
fun AppTitle() {
    Row(Modifier.background(MaterialTheme.colorScheme.secondary)) {
        Image(
            painter = painterResource(id = R.drawable.league_of_legends_logo),
            contentDescription = "League of Legends Logo",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "Stats",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}