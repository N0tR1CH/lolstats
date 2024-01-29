package com.polibudaguys.lolstats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.polibudaguys.lolstats.composables.AppBottomBar
import com.polibudaguys.lolstats.composables.AppTopBar

@Preview
@Composable
fun App() {
    Scaffold(
        topBar = {
            AppTopBar()
        },
        bottomBar = {
            AppBottomBar()
        },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Hello world")
        }
    }
}
