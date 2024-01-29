package com.polibudaguys.lolstats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polibudaguys.lolstats.composables.AppTitle
import com.polibudaguys.lolstats.composables.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            AppTopBar()
        },
        bottomBar = {

        }
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Hello world")
        }
    }
}
