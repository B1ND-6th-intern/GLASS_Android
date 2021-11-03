package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.CommentUploadRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetailViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val detailPost = MutableLiveData<Writing>()
    val commentsList = MutableLiveData<Comments>()
    val comment = MutableLiveData<String>()
    val onEmptyCommentEvent = SingleLiveEvent<Unit>()
    val onUploadEvent = SingleLiveEvent<Unit>()
    val writingId = MutableLiveData<String>()
    val onMenuEvent = SingleLiveEvent<Unit>()
    val onHeartEvent = SingleLiveEvent<Unit>()

    val message = MutableLiveData<String>()

    // 성공 : 1, 실패 : 0
    val statusDeletePost = MutableLiveData<Int>()

    fun onClickBack() {
        onBackEvent.call()
    }

    fun getDetailPost(id: String) {
        val getDetailCall = RetrofitClient.detailInterface.detailPost(id)

        getDetailCall.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    detailPost.value = result?.writing
                    Log.d("Retrofit2", "onResponse: 성공 Detail")
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.error
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

    fun onClickUpload() {
        if (comment.value == null) {
            onEmptyCommentEvent.call()
        } else {
            val commentUpload = RetrofitClient.commentUploadInterface.commentUpload(
                CommentUploadRequest(
                    comment.value?: "",
                    writingId.value?:""
                )
            )

            commentUpload.enqueue(object : Callback<CommentUploadResponse> {
                override fun onResponse(
                    call: Call<CommentUploadResponse>,
                    response: Response<CommentUploadResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Retrofit2", "onResponse: 성공 comment upload")
                        val result = response.body()
                        commentsList.value = result?.comment
                        onUploadEvent.call()
                    } else {
                        val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                            ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                        message.value = errorBody?.error
                        Log.d("Retrofit2", "onResponse: ${response.code()} comment upload")
                    }
                }

                override fun onFailure(call: Call<CommentUploadResponse>, t: Throwable) {
                    Log.d("Retrofit2", "onFailure: $t")
                }

            })
        }
    }

    fun onClickMenu() {
        onMenuEvent.call()
    }

    fun deletePost(id : String) {
        val deletePostCall = RetrofitClient.detailInterface.deletingPost(id)

        deletePostCall.enqueue(object : Callback<DeletePostResponse> {
            override fun onResponse(
                call: Call<DeletePostResponse>,
                response: Response<DeletePostResponse>
            ) {
                if (response.isSuccessful) {
                    statusDeletePost.value = 1
                    Log.d("Retrofit2", "onResponse: 성공 deletePost")
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.error
                    statusDeletePost.value = 0
                    Log.d("Retrofit2", "onResponse: ${response.code()} deletePost")
                }
            }

            override fun onFailure(call: Call<DeletePostResponse>, t: Throwable) {
                statusDeletePost.value = 0
                Log.d("Retrofit2", "onFailure: $t deletePost")
            }

        })
    }

    fun onClickHeart() {
        onHeartEvent.call()
    }

    fun onClickLikeBtn(id: String) {
        val clickLikeCall = RetrofitClient.likeInterface.editLike(id)

        clickLikeCall.enqueue(object : Callback<LikeResponse> {
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                if (response.isSuccessful) {
                    Log.d("Retrofit2", "onResponse: 성공 like")
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.error
                    Log.d("Retrofit2", "onResponse: ${response.code()} like")
                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t like")
            }

        })
    }
}