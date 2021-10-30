package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.RetrofitClient
import kr.hs.dgsw.smartschool.glass_android.network.request.ProfileEditRequest
import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileEditAvatarResponse
import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileEditResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditProfileViewModel : ViewModel() {
    val onBackProfileEvent = SingleLiveEvent<Unit>()
    val onAvatarCheckEvent = SingleLiveEvent<Unit>()
    val onEditCheckEvent = SingleLiveEvent<Unit>()
    val onChangeProfileImageEvent = SingleLiveEvent<Unit>()

    val name = MutableLiveData<String>()
    val introduction = MutableLiveData<String>()

    var avatar = MutableLiveData<ArrayList<File>>(arrayListOf())

    fun onClickBtnBackProfile() {
        onBackProfileEvent.call()
    }

    fun onClickEditCheck() {
        val editProfileCall = RetrofitClient.profileEditInterface.editProfile(
            ProfileEditRequest(
                name.value ?: "",
                introduction.value ?: ""
            )
        )

        editProfileCall.enqueue(object : Callback<ProfileEditResponse> {
            override fun onResponse(
                call: Call<ProfileEditResponse>,
                response: Response<ProfileEditResponse>
            ) {
                if (response.isSuccessful) {
                    onEditCheckEvent.call()
                    Log.d("Retrofit2", "onResponse: 성공 editProfile")
                } else {
                    Log.d("Retrofit2", "onResponse: ${response.code()} editProfile")
                }
            }

            override fun onFailure(call: Call<ProfileEditResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t editProfile")
            }

        })
    }

    fun onClickAvatarCheck() {
        val avatarCall = RetrofitClient.profileEditAvatarInterface.editProfileAvatar(
            avatar.value?.map { MultipartBody.Part.createFormData(
                "newAvatar",
                it.name,
                RequestBody.create("image/${it.name.split(".")[1]}".toMediaTypeOrNull(), it)
            )}
        )

        avatarCall.enqueue(object : Callback<ProfileEditAvatarResponse> {
            override fun onResponse(
                call: Call<ProfileEditAvatarResponse>,
                response: Response<ProfileEditAvatarResponse>
            ) {
                if (response.isSuccessful) {
                    onAvatarCheckEvent.call()
                    Log.d("Retrofit2", "onResponse: 성공 edit avatar")
                } else {
                    Log.d("Retrofit2", "onResponse: ${response.code()} edit avatar")
                }
            }

            override fun onFailure(call: Call<ProfileEditAvatarResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t edit avatar")
            }

        })
    }

    fun onClickTvChangeProfileImage() {
        onChangeProfileImageEvent.call()
    }

}