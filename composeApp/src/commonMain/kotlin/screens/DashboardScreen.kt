package screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sqDelight.DatabaseDriverFactory

@Composable
fun DashboardScreen(databaseDriverFactory: DatabaseDriverFactory) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {

            BottomNavigation(
                backgroundColor = Color.White,
                elevation = 8.dp,
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Users") },
                    label = { Text("Users") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favourite") },
                    label = { Text("Favourite") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = "To-Do") },
                    label = { Text("To-Do") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    ) {
        when (selectedTab) {
            0 -> UserScreen(databaseDriverFactory)
            1 -> FavoritePage(databaseDriverFactory)
            2 -> ToDoPage()
            3 -> ProfilePage()
        }
    }
}









