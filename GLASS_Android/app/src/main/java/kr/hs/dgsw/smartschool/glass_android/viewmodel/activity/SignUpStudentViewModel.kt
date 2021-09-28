package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.SignUpRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignUpStudentViewModel : ViewModel() {
    val onSignUpEvent = SingleLiveEvent<Unit>()
    val onBackSelect = SingleLiveEvent<Unit>()

    val name = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val classNumber = MutableLiveData<String>()
    val stuNumber = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val password2 = MutableLiveData<String>()
    val permission: Int = 1
    val isAgree = MutableLiveData(false)


    fun onClickSignUp() {


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

        RetrofitClient.signUpInterface.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                Log.d("Retrofit2", "onResponse : 회원가입 성공")
                onSignUpEvent.call()
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable){
                Log.d("Retrofit2", "onFailure: $t")
            }

        })


        /* call.enqueue(object: Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("Retrofit2", "onResponse: 회원가입 성공!")
                    onSignUpEvent.call()
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }
        }) */
    }

    fun onClickBackSelect() {
        onBackSelect.call()
    }

    fun onClickPersonalInfo() {
        isAgree.value = isAgree.value != true
    }

}