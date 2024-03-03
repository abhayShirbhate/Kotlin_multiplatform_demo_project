package viewmodel

import repository.UserRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import models.User

class FavoriteViewModel(private val repository: UserRepository): ViewModel() {

    private val _userListFlow = MutableStateFlow<List<User>>(emptyList())
    val userListFlow: Flow<List<User>> get() = _userListFlow

//    fun getAllFavoriteUsers(){
//        repository.getAllFavoriteUsers(this)
//    }
//
//    override fun onGetFavoriteUserCallBack(list: List<UserModel>) {
//        _userListFlow.value = list
//    }
}