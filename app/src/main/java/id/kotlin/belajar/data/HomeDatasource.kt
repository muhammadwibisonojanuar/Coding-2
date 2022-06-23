package id.kotlin.belajar.data


import id.kotlin.belajar.BuildConfig
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeDatasource {
    @GET("/3/discover/movie")
    fun discoverMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Single<HomeResponse>
}