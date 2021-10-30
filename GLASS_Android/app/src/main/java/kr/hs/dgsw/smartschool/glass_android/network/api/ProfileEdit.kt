package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.ProfileEditRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileEditResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileEdit {
    @POST("users/edit")
    fun editProfile(@Body profileEditRequest: ProfileEditRequest) : Call<ProfileEditResponse>
}