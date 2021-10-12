package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.ConfirmRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ConfirmResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.EmailResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.coroutines.coroutineContext

class CheckEmailViewModel : ViewModel() {
    val onCheckEvent = SingleLiveEvent<Unit>()
    val onBackSignUpEvent = SingleLiveEvent<Unit>()
    val onResendEmailEvent = SingleLiveEvent<Unit>()

    val confirm = MutableLiveData<String>()
    val timeover: Boolean = false

    val sendCount: Int = 0
    val message: String = ""

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

    fun onClickTvResend() {
        val emailResponse = RetrofitClient.signUpInterface.sendEmail()

        // Email 보내기
        emailResponse.enqueue(object : retrofit2.Callback<EmailResponse> {
            override fun onResponse(emailCall: Call<EmailResponse>, emailResponse: Response<EmailResponse>) {
                if (emailResponse.isSuccessful) {
                    onResendEmailEvent.call()
                } else {
                    Log.d("Retrofit2", "onResponse: oh no")
                }
            }

            override fun onFailure(emailCall: Call<EmailResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }
        })
    }

    fun onClickBackSignUp() {
        onBackSignUpEvent.call()
    }

}