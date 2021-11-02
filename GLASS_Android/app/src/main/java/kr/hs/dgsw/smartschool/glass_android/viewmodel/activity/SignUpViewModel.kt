package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.SignUpRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.EmailResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.ErrorResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val onSignUpEvent = SingleLiveEvent<Unit>()
    val onBackSelectEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()

    val onEmailEvent = SingleLiveEvent<Unit>()

    val message = MutableLiveData<String>()


    val name = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val classNumber = MutableLiveData<String>()
    val stuNumber = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val password2 = MutableLiveData<String>()
    var permission = -1
    val isAgree = MutableLiveData<Boolean>(false)


    fun onClickSignUp() {

        if(permission != 0){
            grade.value = "0"
            classNumber.value = "0"
            stuNumber.value = "0"
        }

        if(password.value != null && password2.value != null && email.value != null && name.value != null && grade.value != null && classNumber.value != null && stuNumber.value != null) {
            val signUpRequest = SignUpRequest(
                    password.value ?: "",
                    password2.value ?: "",
                    email.value ?: "",
                    name.value ?: "",
                    permission,
                    isAgree.value!!,
                    grade.value!!.toInt(),
                    classNumber.value!!.toInt(),
                    stuNumber.value!!.toInt()
            )
            val emailResponse = RetrofitClient.signUpInterface.sendEmail()

            RetrofitClient.signUpInterface.signUp(signUpRequest)
                .enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Retrofit2", "onResponse : 회원가입 성공")
                            onSignUpEvent.call()

                            // Email 보내기
                            emailResponse.enqueue(object : Callback<EmailResponse> {
                                override fun onResponse(
                                    emailCall: Call<EmailResponse>,
                                    emailResponse: Response<EmailResponse>
                                ) {
                                    if (emailResponse.isSuccessful) {
                                        Log.d("Retrofit2", "onResponse: 성공")
                                    } else {
                                        val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(emailResponse.errorBody())
                                        message.value = errorBody?.error
                                        Log.d("Retrofit2", "onResponse: oh no")
                                    }
                                }

                                override fun onFailure(
                                    emailCall: Call<EmailResponse>,
                                    t: Throwable
                                ) {
                                    Log.d("Retrofit2", "onFailure: $t")
                                }
                            })

                        } else {
                            Log.d("Retrofit2", "onResponse: ${response.code()}")
                            val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                            message.value = errorBody?.error
                        }
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        message.value = "서버 오류입니다."
                        Log.d("Retrofit2", "onFailure: $t")
                    }

                })
        } else {
            onEmptyEvent.call()
        }
    }

    fun onClickBackSelect() {
        onBackSelectEvent.call()
    }

    fun onClickPersonalInfo() {
        isAgree.value = isAgree.value != true
    }

}