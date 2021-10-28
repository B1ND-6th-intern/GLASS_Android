package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.response.popularPostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PopularPost {
    @GET("posts/popular")
    fun popularPost() : Call<popularPostResponse>
}