package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.LoginRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest) : retrofit2.Call<LoginResponse>
}