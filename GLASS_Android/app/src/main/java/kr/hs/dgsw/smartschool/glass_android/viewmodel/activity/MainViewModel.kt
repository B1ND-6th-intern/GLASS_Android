package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.response.ErrorResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.MyProfileResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val onPostEvent = SingleLiveEvent<Unit>()
    val onSettingEvent = SingleLiveEvent<Unit>()
    val message = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val permissionVal = MutableLiveData<Int>()

//    val count = MutableLiveData<Int>(0)

    fun getPermission() {
        val getIdCall = RetrofitClient.myProfileInterface.getMyProfile()

        getIdCall.enqueue(object : Callback<MyProfileResponse> {
            override fun onResponse(
                idCall: Call<MyProfileResponse>,
                idResponse: Response<MyProfileResponse>
            ) {
                if (idResponse.isSuccessful) {
                    val result1 = idResponse.body()
                    id.value = result1?.id

                    val getPermissionCall = RetrofitClient.profileInterface.getUsersInfo(id.value.toString())

                    getPermissionCall.enqueue(object : Callback<ProfileResponse> {
                        override fun onResponse(
                            infoCall: Call<ProfileResponse>,
                            infoResponse: Response<ProfileResponse>
                        ) {
                            if (infoResponse.isSuccessful) {
                                // 성공
                                val result2 = infoResponse.body()
                                permissionVal.value = result2?.user?.permission

                                Log.d("Retrofit2", "onResponse: 성공 프로필 ${permissionVal.value}")
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

    fun onClickBtnPost() {
        onPostEvent.call()
    }

    fun onClickBtnSetting() {
        onSettingEvent.call()
    }
}