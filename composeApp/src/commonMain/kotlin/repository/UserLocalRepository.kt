package repository

import models.User

interface UserLocalDbRepository {
    fun insertUser(user: User)
    fun deleteUser(user: User)
    fun insertAll(list: List<User>,callback:()->Unit)
    fun getUsers(pageSize: Int, pageNo:Int,callback: (list:List<User>) -> Unit)
    fun updateFavourite(user:User)
    fun getAllFavouriteUsers(callback: (userList:List<User>) -> Unit)
}