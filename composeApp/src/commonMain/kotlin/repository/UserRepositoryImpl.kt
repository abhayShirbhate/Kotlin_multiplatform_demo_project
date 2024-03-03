package repository

import Api.ApiClient
import Response.ApiResult
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import listeners.FetchUserApiListener
import models.User

class UserRepositoryImpl(private val apiClient: ApiClient) : UserRepository {

    override fun fetchUsers(page: Int, pageSize: Int, fetchUserApiListener: FetchUserApiListener) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiClient.getUserData(page, pageSize)
                if (response.status.isSuccess()) {
                    val users = response.body<List<User>>()
                    withContext(Dispatchers.Main) {
                        fetchUserApiListener.fetchUserApiListenerCallBack(ApiResult.Success(users))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e is SocketTimeoutException || e is UnresolvedAddressException) {
                        fetchUserApiListener.fetchUserApiListenerCallBack(
                            ApiResult.NoInternetConnection(
                                "Please check internet connection and try again!!"
                            )
                        )
                    } else {
                        fetchUserApiListener.fetchUserApiListenerCallBack(ApiResult.Error("Something went wrong and please try again!!"))
                    }
                }
            }
        }
    }


}