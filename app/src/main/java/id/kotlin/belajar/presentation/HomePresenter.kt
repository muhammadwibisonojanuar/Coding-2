package id.kotlin.belajar.presentation

import id.kotlin.belajar.data.HomeDatasource
import id.kotlin.belajar.data.HomeResponse
import id.kotlin.belajar.di.module.Networkmodule
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val view: HomeView, datasource: HomeDatasource) {
    fun discoverMovie(){
        view.onShowLoading()

        val dataSource = Networkmodule.providesHttpAdapter(client = OkHttpClient()).create(HomeDatasource::class.java)
        dataSource.discoverMovie().enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>){
                view.onHideLoading()
                view.onResponse(((response.body()?.results ?: emptyList())))
            }
            override fun onFailure(call: Call<HomeResponse>, t:Throwable){
                view.onHideLoading()
                view.onFailure(t)
            }
        })
    }
}
