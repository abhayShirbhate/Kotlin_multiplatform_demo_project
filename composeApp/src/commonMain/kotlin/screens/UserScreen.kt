package screens

import Api.ApiClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import repository.UserRepositoryImpl


@Composable
fun UserScreen() {
    val viewModel: UserViewModel = getViewModel(Unit, viewModelFactory {
        UserViewModel(
            UserRepositoryImpl(
                ApiClient()
            )
        )
    })

    val users by remember(viewModel.listItems) {
        mutableStateOf(viewModel.listItems)
    }

    val progressBarVisibility by viewModel.progressBarVisibility


    var isAlertDialogVisible by remember {
        mutableStateOf(false)
    }

    var message by remember {
        mutableStateOf(viewModel.errorLiveData.value)
    }

    viewModel.errorLiveData.addObserver { errorMsg ->
        if (errorMsg == null) return@addObserver
        message = errorMsg
        isAlertDialogVisible = true

    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(users.size) { i ->
            val user = users[i]
            swipeableContainer(user, viewModel)

            Spacer(modifier = Modifier.height(8.dp))

        }
        item {
            if (!progressBarVisibility) {
                viewModel.fetchUsers()
            }


        }
    }

    if (isAlertDialogVisible) {
        AlertDialogWithSingleButton(
            "Error",
            "",
            "Try Again"
        ) {
            viewModel.fetchUsers()
            isAlertDialogVisible = false
        }
    }
    if (progressBarVisibility) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Blue,
                strokeWidth = 2.dp
            )

        }
    }


}

