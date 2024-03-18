package repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.User
import sqDelight.UserDao

class UserLocalDbRepositoryImpl(private val userDao: UserDao) : UserLocalDbRepository {
    override fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user.id.toLong())
    }

    override fun insertAll(list: List<User>,callback:()->Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach { item ->
                insertUser(item)
            }
            withContext(Dispatchers.Main){
                callback()
            }
        }
    }

    override fun getUsers(pageSize: Int, pageNo:Int,callback: (list:List<User>) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val list =  userDao.getAllUsers(pageNo.toLong(),pageSize.toLong())
            withContext(Dispatchers.Main){
                callback(list)
            }
        }
    }

    override fun updateFavourite(user: User) {
        userDao.updateFavouriteState(user.id.toLong(),user.isFavorite)
    }

    override fun getAllFavouriteUsers(callback: (userList: List<User>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = userDao.getAllFavouriteUser()
            withContext(Dispatchers.Main){
                callback(list)
            }
        }
    }
}