package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.ErrorResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.PopularPostResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.Writing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    val onSearchEvent = SingleLiveEvent<Unit>()
    val onPopularDetailEvent = SingleLiveEvent<String>()
    val popularList = MutableLiveData<List<Writing>>()
    val clickPermission = MutableLiveData<Int>()
    val message = MutableLiveData<String>()

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
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.error
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PopularPostResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }
        })
    }

    fun onClickImage(permission : Int) {
        clickPermission.value = permission
        onPopularDetailEvent.value = popularList.value.toString()
    }
}