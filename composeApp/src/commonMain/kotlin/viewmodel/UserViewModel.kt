package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import Response.ApiResult
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import listeners.FetchUserApiListener
import models.User
import repository.UserRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import repository.UserLocalDbRepository
import sqDelight.UserDao

class UserViewModel (private val repository: UserRepository,private val userLocalDbRepository: UserLocalDbRepository): ViewModel(), FetchUserApiListener {

    private val _errorLiveDate = MutableLiveData<String?>(null)
    val errorLiveData get() = _errorLiveDate
    private val _listItems = mutableStateListOf<User>()
    val listItems: List<User> get() = _listItems
    private var _progressBarVisibility =  mutableStateOf(false)
    val progressBarVisibility get() = _progressBarVisibility

    private var pageNo = 1
    private val pageSize = 10

    init {
        fetchUsers()
    }

    fun fetchUsers(){
        if (pageNo==-1) return
        _progressBarVisibility.value = true
        repository.fetchUsers(pageNo,pageSize,this)
    }

    fun savedUsersListToDB(userList: List<User>){
        userLocalDbRepository.insertAll(userList){
            getUsersFromLocalDb()
        }
        pageNo++
        if (userList.size<10) pageNo = -1
    }
    fun getUsersFromLocalDb(){
        userLocalDbRepository.getUsers(pageNo,pageSize){list ->
            _listItems.addAll(list)
        }
    }

    fun updateFavouriteStatus(user: User){
        userLocalDbRepository.updateFavourite(user)
    }

    override fun fetchUserApiListenerCallBack(result: ApiResult<List<User>>) {
        when (result) {
            is ApiResult.Success -> {
                savedUsersListToDB(result.data)
                _progressBarVisibility.value = false
            }

            is ApiResult.NoInternetConnection -> {
                _errorLiveDate.value = result.msg
                _progressBarVisibility.value = false
            }

            is ApiResult.Error -> {
                _progressBarVisibility.value = false
                _errorLiveDate.value = result.msg
            }
        }
    }



}