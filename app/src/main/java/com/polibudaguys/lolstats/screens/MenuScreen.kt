package com.polibudaguys.lolstats.screens

import android.R.attr.data
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.polibudaguys.lolstats.MainActivity


@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com")


             }) {
            Text("OPEN GMAPS WITH YOUR LOCATION")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = { /*TODO*/ }) {
            Text("OPEN YOUTUBE WITH LOL TRAILER")
        }
    }
}