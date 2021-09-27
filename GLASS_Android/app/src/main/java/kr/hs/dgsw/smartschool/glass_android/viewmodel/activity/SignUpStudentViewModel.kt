package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class SignUpStudentViewModel : ViewModel() {
    val onSignUpEvent = SingleLiveEvent<Unit>()
    val onBackSelect = SingleLiveEvent<Unit>()

    fun onClickSignUp() {
        onSignUpEvent.call()
    }
    fun onClickBackSelect() {
        onBackSelect.call()
    }

}