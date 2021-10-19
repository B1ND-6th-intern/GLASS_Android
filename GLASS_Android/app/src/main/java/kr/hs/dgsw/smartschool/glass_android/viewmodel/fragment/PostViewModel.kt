package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.SecondPostingRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.FirstPostingResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.SecondPostingResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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

    // first
    var images = MutableLiveData<ArrayList<File>>(arrayListOf())


    // second
    val contentText = MutableLiveData<String>()
    val hashTag = MutableLiveData<String>()

    val hashTags = MutableLiveData<List<String>>()
    val secondImages = MutableLiveData<List<String>>(arrayListOf())
    val token = MutableLiveData<String>()

    fun onClickBtnAddImage() {
        onImageEvent.call()
    }

    fun onClickBtnPost() {
        val firstCall = RetrofitClient.postingInterface.firstPosting(
            images.value?.map { MultipartBody.Part.createFormData(
                "img",
                it.name,
                RequestBody.create("image/${it.name.split(".")[1]}".toMediaTypeOrNull(), it)
            )} ?: listOf()
        )

        firstCall.enqueue(object : Callback<FirstPostingResponse> {
            override fun onResponse(
                call: Call<FirstPostingResponse>,
                response: Response<FirstPostingResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("Retrofit2", "onResponse: 이미지 포스팅 성공")

                    val firstResult = response.body()
                    
                    secondImages.value = firstResult?.images
                    hashTags.value = hashTag.value?.split(',')

                    val secondCall = RetrofitClient.postingInterface.secondPosting(
                        SecondPostingRequest(contentText.value?: "",
                            hashTags.value?: listOf(),
                            secondImages.value?: listOf())
                    )
                    secondCall.enqueue(object : Callback<SecondPostingResponse> {
                        override fun onResponse(
                            secondCall: Call<SecondPostingResponse>,
                            secondResponse: Response<SecondPostingResponse>
                        ) {
                            if (secondResponse.isSuccessful) {
                                Log.d("Retrofit2", "onResponse: 성공")
                                onPostEvent.call()
                            } else {
                                Log.d("Retrofit2", "onResponse: 실패 ${secondResponse.code()}")
                            }
                        }

                        override fun onFailure(seconCall: Call<SecondPostingResponse>, secondT: Throwable) {
                            Log.d("Retrofit2", "onFailure: $secondT")
                        }

                    })

                } else {
                    Log.d("Retrofit2", "onResponse: 실패 400 ${response.code()}")
                }
            }

            override fun onFailure(call: Call<FirstPostingResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

    fun onClickBtnBack() {
        onBackEvent.call()
    }

}