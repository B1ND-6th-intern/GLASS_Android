package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.Comments
import kr.hs.dgsw.smartschool.glass_android.network.response.DetailResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.Writing
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val detailPost = MutableLiveData<Writing>()

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
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }


}