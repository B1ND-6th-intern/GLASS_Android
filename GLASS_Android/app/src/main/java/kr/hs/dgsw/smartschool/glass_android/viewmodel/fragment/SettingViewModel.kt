package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class SettingViewModel : ViewModel() {
    val onBackProfileEvent = SingleLiveEvent<Unit>()
    val onLogoutEvent = SingleLiveEvent<Unit>()
    val onChangePasswordEvent = SingleLiveEvent<Unit>()

    fun onClickBtnBackProfile() {
        onBackProfileEvent.call()
    }

    fun onClickLogout() {
        onLogoutEvent.call()
    }

    fun onClickPassword() {
        onChangePasswordEvent.call()
    }
}