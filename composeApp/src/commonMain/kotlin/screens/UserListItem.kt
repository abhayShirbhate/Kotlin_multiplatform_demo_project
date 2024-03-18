package screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import models.User
import org.jetbrains.compose.ui.tooling.preview.Preview
import swipe.SwipeAction
import swipe.SwipeableActionsBox
import viewmodel.UserViewModel

@Composable
fun UserListItem(user: User) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,

        ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Gender: ${user.gender}",
                    style = MaterialTheme.typography.body2
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (user.status == "active") Color.Green else Color.Red)
                    .offset(x = 8.dp, y = 8.dp)
                    .padding(4.dp)
            )


        }
    }
}


@Composable
fun swipeableContainer(userModel: User, viewModel: UserViewModel) {

    val user by remember { mutableStateOf(userModel) }


    val favorite = SwipeAction(
        icon = {
            defaultIcon(
                icon = Icons.Default.Favorite,
                title = "Make favorite",
                onclick = {
                }
            )
        },
        background = Color.Green,
        onSwipe = {
            user.isFavorite = true
            viewModel.updateFavouriteStatus(user)
                  },
        weight = 1.0,
        isUndo = false
    )

    val favoriteBoarder = SwipeAction(
        onSwipe = {
            user.isFavorite = false
            viewModel.updateFavouriteStatus(user)
                  },
        icon = {
            defaultIcon(
                icon = Icons.Default.FavoriteBorder,
                title = "Remove as favorite",
                onclick = {
                }
            )
        },
        background = Color.Red,
        weight = 1.0,
        isUndo = false
    )

    SwipeableActionsBox(
        startActions = listOf(favorite, favoriteBoarder),
        endActions = listOf(favorite, favoriteBoarder),
        dragStateDelay = 1,
        userModel = user
    ) {
        UserListItem(user)
    }
}

@Composable
fun defaultIcon(
    icon: ImageVector,
    title: String,
    onclick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(0.5F)
                .clickable {
                    onclick()
                }
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview
@Composable
fun PreviewUserListItem() {
    UserListItem(
        User(
            id = 1,
            name = "John Doe",
            email = "john@example.com",
            gender = "male",
            status = "active"
        )
    )
}
