package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.FirstPostingRequest
import kr.hs.dgsw.smartschool.glass_android.network.request.SecondPostingRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.FirstPostingResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.SecondPostingResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Posting {
    @Multipart
    @POST("writings/upload/imgs")
    fun firstPosting(@Part file: List<MultipartBody.Part>) : retrofit2.Call<FirstPostingResponse>

    @POST("writings/upload")
    fun secondPosting(@Body secondPostingRequest: SecondPostingRequest) : retrofit2.Call<SecondPostingResponse>
}