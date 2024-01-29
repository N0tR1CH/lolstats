package com.polibudaguys.lolstats.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppTopBar() {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary,
    ), title = {
        AppTitle()
    }, navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    })
}