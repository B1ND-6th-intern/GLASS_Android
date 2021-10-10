package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class EditProfileViewModel : ViewModel() {
    val onBackProfileEvent = SingleLiveEvent<Unit>()
    val onEditCheckEvent = SingleLiveEvent<Unit>()
    val onChangeProfileImageEvent = SingleLiveEvent<Unit>()

    fun onClickBtnBackProfile() {
        onBackProfileEvent.call()
    }

    fun onClickBtnEditCheck() {
        onEditCheckEvent.call()
    }

    fun onClickTvChangeProfileImage() {
        onChangeProfileImageEvent.call()
    }

}