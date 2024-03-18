package viewmodel

import repository.UserRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import models.User
import repository.UserLocalDbRepository

class FavoriteViewModel(private val userLocalDbRepository: UserLocalDbRepository) : ViewModel() {

    private val _userListFlow = MutableStateFlow<List<User>>(emptyList())
    val userListFlow: Flow<List<User>> get() = _userListFlow

    fun getAllFavoriteUsers() {
        userLocalDbRepository.getAllFavouriteUsers { userList ->
            _userListFlow.value = userList
        }
    }

}