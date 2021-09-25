package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class LoginViewModel : ViewModel() {
    val onLoginEvent = SingleLiveEvent<Unit>()
    val onSignUpEvent = SingleLiveEvent<Unit>()

    fun onClickLogin() {
        onLoginEvent.call()
    }

    fun onClickSignUp() {
        onSignUpEvent.call()
    }
}