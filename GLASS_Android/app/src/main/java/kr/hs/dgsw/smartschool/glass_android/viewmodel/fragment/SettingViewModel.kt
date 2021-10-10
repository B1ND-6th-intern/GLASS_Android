package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class SettingViewModel : ViewModel() {
    val onBackProfileEvent = SingleLiveEvent<Unit>()

    fun onClickBtnBackProfile() {
        onBackProfileEvent.call()
    }
}