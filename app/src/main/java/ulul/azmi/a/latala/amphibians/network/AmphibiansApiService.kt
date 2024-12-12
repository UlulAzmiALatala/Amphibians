package ulul.azmi.a.latala.amphibians.network

import ulul.azmi.a.latala.amphibians.model.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}
