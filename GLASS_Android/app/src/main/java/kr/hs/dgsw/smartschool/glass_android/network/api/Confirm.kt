package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.ConfirmRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ConfirmResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Confirm {
    @POST("users/email-auth")
    fun sendConfirm(@Body confirmRequest: ConfirmRequest) : retrofit2.Call<ConfirmResponse>
}