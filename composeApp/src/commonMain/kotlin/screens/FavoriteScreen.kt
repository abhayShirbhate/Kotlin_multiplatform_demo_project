package screens

import Api.ApiClient
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import viewmodel.FavoriteViewModel
import models.User
import repository.UserRepositoryImpl

@Composable
fun FavoritePage() {
    val viewModel: FavoriteViewModel = getViewModel(Unit, viewModelFactory {
        FavoriteViewModel(
            UserRepositoryImpl(
                ApiClient()
            )
        )
    })

    val userList: List<User> by viewModel.userListFlow.collectAsState(initial = emptyList())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(userList.size) { i ->

            UserListItem(userList[i])
            Spacer(modifier = Modifier.height(8.dp))
        }
    }


//    viewModel.getAllFavoriteUsers()
}