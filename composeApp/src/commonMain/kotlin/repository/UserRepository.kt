package repository

import listeners.FetchUserApiListener


interface UserRepository {
    fun fetchUsers(page: Int, pageSize: Int, fetchUserApiListener: FetchUserApiListener)
}