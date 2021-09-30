package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.SignUpRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.EmailResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUp {
    @POST("join")
    fun signUp(@Body singUpRequest: SignUpRequest) : retrofit2.Call<SignUpResponse>

    @GET("user/email-auth")
    fun sendEmail(@Query ("sendCount") sendCount: Int,
    @Query ("message") message: String): retrofit2.Call<EmailResponse>
}