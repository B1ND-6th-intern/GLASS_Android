package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.ChangePwRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ChangePwResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Setting {
    @POST("users/change-password")
    fun changePw(@Body changePwRequest: ChangePwRequest) : Call<ChangePwResponse>
}