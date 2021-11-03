package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.ErrorResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.HomeResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.LikeResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val postList = MutableLiveData<List<Writings>>()
    val id = MutableLiveData<String>()
    val message = MutableLiveData<String>()

    fun getHomePost() {
        val getPostCall = RetrofitClient.homeInterface.homePost()

        getPostCall.enqueue(object: Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    postList.value = result?.writings

                    Log.d("Retrofit2", "onResponse: 성공")

                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.error
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

    fun onClickLikeBtn() {
        val clickLikeCall = RetrofitClient.likeInterface.editLike(id.value.toString())

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