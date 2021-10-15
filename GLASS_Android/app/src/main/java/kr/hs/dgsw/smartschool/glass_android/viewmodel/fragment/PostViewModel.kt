package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.FirstPostingResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PostViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val onImageEvent = SingleLiveEvent<Unit>()
    val onPostEvent = SingleLiveEvent<Unit>()

    var images = MutableLiveData<ArrayList<File>>(arrayListOf())

    fun onClickBtnAddImage() {
        onImageEvent.call()
    }

    fun onClickBtnPost() {
        val firstCall = RetrofitClient.postingInterface.firstPosting(
            images.value?.map { MultipartBody.Part.createFormData(
                "file",
                it.name,
                RequestBody.create(MediaType.parse("jpg"), it)

//                        MultipartBody.Part.createFormData(
//                        "files",
//                file.name,
//                RequestBody.create("image/${file.name.split(".")[1]}".toMediaTypeOrNull(), file)
//            )
            )} ?: listOf()
        )

        firstCall.enqueue(object : Callback<FirstPostingResponse> {
            override fun onResponse(
                call: Call<FirstPostingResponse>,
                response: Response<FirstPostingResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("Retrofit2", "onResponse: 이미지 포스팅 성공")
                } else {
                    Log.d("Retrofit2", "onResponse: 실패 400")
                }
            }

            override fun onFailure(call: Call<FirstPostingResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
        onPostEvent.call()
    }

    fun onClickBtnBack() {
        onBackEvent.call()
    }

}