package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.response.DeletePostResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.DetailResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface Detail {
    @GET("writings/{id}")
    fun detailPost(@Path("id") id: String): Call<DetailResponse>

    @DELETE("writings/delete/{id}")
    fun deletingPost(@Path("id") id: String): Call<DeletePostResponse>
}