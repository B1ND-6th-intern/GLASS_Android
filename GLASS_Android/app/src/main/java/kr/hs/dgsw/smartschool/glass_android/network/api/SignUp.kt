package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.SignUpRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUp {
    @POST("join")
    fun signUp(@Body singUpRequest: SignUpRequest) : retrofit2.Call<SignUpResponse>
}