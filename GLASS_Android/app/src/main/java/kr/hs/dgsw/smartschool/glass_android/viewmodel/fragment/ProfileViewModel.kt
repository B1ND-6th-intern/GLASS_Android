package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class ProfileViewModel: ViewModel() {
    val onEditProfileEvent = SingleLiveEvent<Unit>()

    fun onClickBtnEditProfile() {
        onEditProfileEvent.call()
    }

}