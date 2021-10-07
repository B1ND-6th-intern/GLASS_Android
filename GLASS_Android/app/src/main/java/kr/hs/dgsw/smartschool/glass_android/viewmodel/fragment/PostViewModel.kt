package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class PostViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()

    fun onClickBtnBack() {
        onBackEvent.call()
    }

}