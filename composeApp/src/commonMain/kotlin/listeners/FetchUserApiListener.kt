package listeners

import Response.ApiResult
import models.User

interface FetchUserApiListener {
    fun fetchUserApiListenerCallBack(result: ApiResult<List<User>>)
}