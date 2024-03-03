package Api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiClient {

    private val http = HttpClient {
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getUserData(pageNo: Int, pageSize: Int): HttpResponse {
        return http.get("https://gorest.co.in/public/v2/users?page=$pageNo&per_page=$pageSize")
    }
}