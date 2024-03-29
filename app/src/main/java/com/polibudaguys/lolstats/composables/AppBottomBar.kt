package com.polibudaguys.lolstats.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomBar(navController: NavHostController) {
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            for (bottomBarItem: BottomBarItem in BottomBarItems.items) {
                AppBottomBarItem(bottomBarItem, navController, currentRoute)
            }
        }
    }
}

data class BottomBarItem(
    var name: String,
    var icon: ImageVector,
    var routeName: String,
)


object BottomBarItems {
    val Search = BottomBarItem(
        name = "Search",
        icon = Icons.Filled.Search,
        routeName = "search",
    )

    val History = BottomBarItem(
        name = "History",
        icon = Icons.Filled.List,
        routeName = "history",
    )

    val Menu = BottomBarItem(
        name = "Menu",
        icon = Icons.Filled.Menu,
        routeName = "menu",
    )

    var items: List<BottomBarItem> = listOf(Search, History, Menu)
}

@Composable
fun AppBottomBarItem(
    bottomBarItem: BottomBarItem,
    navController: NavHostController,
    currentRoute: String?,
) {
    val isSelected: Boolean = currentRoute == bottomBarItem.routeName

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(onClick = {
            navController.navigate(bottomBarItem.routeName)
        }) {
            Icon(
                imageVector = bottomBarItem.icon,
                contentDescription = bottomBarItem.name,
                modifier = Modifier.size(40.dp),
                tint = if (isSelected) MaterialTheme.colorScheme.onSecondary
                else MaterialTheme.colorScheme.inverseSurface,
            )
        }
        Text(
            text = bottomBarItem.name, fontWeight = FontWeight.Bold,
            color = if (isSelected) MaterialTheme.colorScheme.onSecondary
            else MaterialTheme.colorScheme.inverseSurface,
        )
    }
}
