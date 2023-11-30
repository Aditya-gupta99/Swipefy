package com.sparklead.swipefy.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.presentation.navigation.Screen
import com.sparklead.swipefy.presentation.theme.DarkGreen

@Composable
fun NavigationBar(
    route: String,
    onRouteSelected: (targetRoute: String) -> Unit
) {
    val tabs = remember {
        listOf(
            Screen.HomeScreen,
            Screen.SongListScreen,
            Screen.ProfileScreen
        )
    }
    BottomNavigation(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        tabs.forEach { item ->
            val targetRoute = item.route
            val selected = route.contains(targetRoute)
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (selected) Color.White else Color.White.copy(0.4f)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = if(selected) Color.White else Color.White.copy(0.4f)
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                selected = selected,
                onClick = { onRouteSelected(targetRoute) }
            )
        }
    }
}