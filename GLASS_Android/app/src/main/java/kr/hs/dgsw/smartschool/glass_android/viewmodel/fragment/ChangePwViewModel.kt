package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.ChangePwRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ChangePwResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.PopularPostResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.Writing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePwViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val onChangePwEvent = SingleLiveEvent<Unit>()
    val oldPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val newPasswordConfirmation = MutableLiveData<String>()

    fun onClickBack() {
        onBackEvent.call()
    }

    fun onClickChange() {
        val changePwRequest = ChangePwRequest(
            oldPassword.value ?: "",
            newPassword.value ?: "",
            newPasswordConfirmation.value ?: ""
        )

        RetrofitClient.settingInterface.changePw(changePwRequest)
            .enqueue(object : Callback<ChangePwResponse> {
                override fun onResponse(
                    call: Call<ChangePwResponse>,
                    response: Response<ChangePwResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Retrofit2", "onResponse: 성공 changePw")
                        onChangePwEvent.call()
                    } else {
                        Log.d("Retrofit2", "onResponse: ${response.code()} changePw")
                    }
                }

                override fun onFailure(call: Call<ChangePwResponse>, t: Throwable) {
                    Log.d("Retrofit2", "onFailure: $t changePw")
                }
            })

    }
}