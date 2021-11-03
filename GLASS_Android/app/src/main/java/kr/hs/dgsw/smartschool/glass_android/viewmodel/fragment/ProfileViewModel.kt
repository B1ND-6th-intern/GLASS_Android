package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel: ViewModel() {
    val onEditProfileEvent = SingleLiveEvent<Unit>()
    val userInfo = MutableLiveData<User>()
    var id = MutableLiveData<String>()

    val message = MutableLiveData<String>()

    fun getProfile() {
        val getProfileIdCall = RetrofitClient.myProfileInterface.getMyProfile()

        getProfileIdCall.enqueue(object : Callback<MyProfileResponse> {
            override fun onResponse(
                idCall: Call<MyProfileResponse>,
                idResponse: Response<MyProfileResponse>
            ) {
                if (idResponse.isSuccessful) {
                    val result1 = idResponse.body()
                    id.value = result1?.id

                    val getProfileCall = RetrofitClient.profileInterface.getUsersInfo(id.value.toString())

                    getProfileCall.enqueue(object : Callback<ProfileResponse> {
                        override fun onResponse(
                            infoCall: Call<ProfileResponse>,
                            infoResponse: Response<ProfileResponse>
                        ) {
                            if (infoResponse.isSuccessful) {
                                // 성공
                                val result2 = infoResponse.body()
                                userInfo.value = result2?.user

                                Log.d("Retrofit2", "onResponse: 성공 프로필 ${userInfo.value}")
                            } else {
                                val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                                    ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(infoResponse.errorBody())
                                message.value = errorBody?.error
                                Log.d("Retrofit2", "onResponse: ${infoResponse.code()} 프로필")
                            }
                        }

                        override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                            Log.d("Retrofit2", "onFailure: $t")
                        }

                    })

                    Log.d("Retrofit2", "onResponse: 성공 id")
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(idResponse.errorBody())
                    message.value = errorBody?.error
                    Log.d("Retrofit2", "onResponse: ${idResponse.code()} id")
                }
            }

            override fun onFailure(call: Call<MyProfileResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

    fun onClickBtnEditProfile() {
        onEditProfileEvent.call()
    }

}