package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Profile {
    @GET("users/{id}")
    fun getUsersInfo(@Path("id") id: String): Call<ProfileResponse>
}