package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.PopularPostResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.Writing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    val onSearchEvent = SingleLiveEvent<Unit>()
    val onPopularDetailEvent = SingleLiveEvent<Unit>()
    val popularList = MutableLiveData<List<Writing>>()

    fun onClickBtnSearch() {
        onSearchEvent.call()
    }

    fun getPopularPost() {
        val getPopularPostCall = RetrofitClient.popularPostInterface.popularPost()

        getPopularPostCall.enqueue(object : Callback<PopularPostResponse> {
            override fun onResponse(
                call: Call<PopularPostResponse>,
                response: Response<PopularPostResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("Retrofit2", "onResponse: 성공 Popular")
                    val result = response.body()
                    popularList.value = result?.writings
                } else {
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PopularPostResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }
    

}