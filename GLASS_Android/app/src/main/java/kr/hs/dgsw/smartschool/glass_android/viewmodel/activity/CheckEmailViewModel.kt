package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class CheckEmailViewModel : ViewModel() {
    val onCheckEvent = SingleLiveEvent<Unit>()
    val onBackSignUpEvent = SingleLiveEvent<Unit>()

    fun onClickCheck() {
        onCheckEvent.call()
    }

    fun onClickBackSignUp() {
        onBackSignUpEvent.call()
    }

}