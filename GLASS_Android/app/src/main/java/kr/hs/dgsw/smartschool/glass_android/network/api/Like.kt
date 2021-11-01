package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.response.LikeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Like {
    @GET("writings/like/{id}")
    fun editLike(@Path("id") id : String): Call<LikeResponse>
}