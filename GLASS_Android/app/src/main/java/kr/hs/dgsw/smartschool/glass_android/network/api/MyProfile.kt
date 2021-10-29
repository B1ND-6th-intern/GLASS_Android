package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.response.MyProfileResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyProfile {
    @GET("users/user-id")
    fun getMyProfile() : Call<MyProfileResponse>
}