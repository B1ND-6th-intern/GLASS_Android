package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.HomeResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val postList = MutableLiveData<List<Writings>>()

    fun getHomePost() {
        val getPostCall = RetrofitClient.homeInterface.homePost()

        getPostCall.enqueue(object: Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    postList.value = result?.writings
                    Log.d("Retrofit2", "onResponse: 성공")

                } else {
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }


}