package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.LoginRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val onLoginEvent = SingleLiveEvent<Unit>()
    val onSignUpEvent = SingleLiveEvent<Unit>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val token = MutableLiveData<String>()

    fun onClickLogin() {
        val call = RetrofitClient.loginInterface.login (
            LoginRequest(email.value?: "",password.value ?: ""))
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful) {
                    Log.d("Retrofit2", "연결 성공")

                    val result = response.body()
                    token.value = result?.token

                    onLoginEvent.call()
                } else {
                    Log.d("Retrofit2", "연결 실패")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }
        })
    }

    fun onClickSignUp() {
        onSignUpEvent.call()
    }
}