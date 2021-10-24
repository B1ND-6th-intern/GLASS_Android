package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.response.HomeResponse
import retrofit2.Call
import retrofit2.http.GET

interface Home {
    @GET("posts")
    fun homePost(): Call<HomeResponse>
}