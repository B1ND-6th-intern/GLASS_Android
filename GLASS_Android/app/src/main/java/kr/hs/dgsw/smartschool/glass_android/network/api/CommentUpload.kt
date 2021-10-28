package kr.hs.dgsw.smartschool.glass_android.network.api

import kr.hs.dgsw.smartschool.glass_android.network.request.CommentUploadRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.CommentUploadResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentUpload {
    @POST("comments/upload")
    fun commentUpload(@Body commentUploadRequest: CommentUploadRequest) : retrofit2.Call<CommentUploadResponse>
}