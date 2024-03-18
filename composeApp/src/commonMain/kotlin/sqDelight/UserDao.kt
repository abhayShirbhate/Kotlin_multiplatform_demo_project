package sqDelight

import com.mounty.camper.cache.MyAppDb
import models.User

class UserDao(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = MyAppDb(databaseDriverFactory.createDriver())
    private val dbQuery = database.myAppDbQueries

    fun insertUser(user: User){
        dbQuery.inserUser(user.id.toLong(),user.name,user.email,user.gender,user.status,if (user.isFavorite) 1L else 0L)
    }

    suspend fun getAllUsers(pageNo:Long,pageSize:Long): List<User>{
        val dbUserList =  dbQuery.selectAllUser(pageSize,(pageNo-1)*pageSize).executeAsList()
        val userList = List<User>(dbUserList.size){i->
            val item = dbUserList[i]
            User(item.id.toInt(), item.name,item.email,item.gender,item.status,item.isFavourite == 0L)
        }
        return userList
    }

    fun deleteUser(id:Long){
        dbQuery.deleteUser(id)
    }
    fun getAllFavouriteUser():List<User>{
        val dbUserList = dbQuery.getAllFavouriteUser().executeAsList()
        val userList = List(dbUserList.size){i->
            val item = dbUserList[i]
            User(item.id.toInt(), item.name,item.email,item.gender,item.status,item.isFavourite == 0L)
        }
        return userList
    }

    fun updateFavouriteState(id:Long,isFavourite:Boolean){
        val fav = if (isFavourite) 1L else 0L
        dbQuery.updateFavouriteState(fav,id)
    }
}