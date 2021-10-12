package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.ConfirmRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ConfirmResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CheckEmailViewModel : ViewModel() {
    val onCheckEvent = SingleLiveEvent<Unit>()
    val onBackSignUpEvent = SingleLiveEvent<Unit>()

    val confirm = MutableLiveData<String>()
    val timeover: Boolean = false

    fun onClickCheck() {

        val confirmRequest = ConfirmRequest(
            confirm.value!!.toInt(),
            timeover
        )

        RetrofitClient.confirmInterface.sendConfirm(confirmRequest).enqueue(object : retrofit2.Callback<ConfirmResponse> {
            override fun onResponse(call: Call<ConfirmResponse>, response: Response<ConfirmResponse>) {
                if (response.isSuccessful) {
                    onCheckEvent.call()
                } else {
                    Log.d("Retrofit2", "onResponse: oh fuck")
                }
            }

            override fun onFailure(call: Call<ConfirmResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

    fun onClickBackSignUp() {
        onBackSignUpEvent.call()
    }

}