package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.ProfileEditAvatarRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileEditAvatarResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProfileEditAvatar {
    @Multipart
    @POST("users/edit/avatar")
    fun editProfileAvatar(@Part newAvatar: List<MultipartBody.Part>?) : Call<ProfileEditAvatarResponse>
}