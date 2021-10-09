package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class PostViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val onImageEvent = SingleLiveEvent<Unit>()
    val onPostEvent = SingleLiveEvent<Unit>()

    fun onClickBtnAddImage() {
        onImageEvent.call()
    }

    fun onClickBtnPost() {
        onPostEvent.call()
    }

    fun onClickBtnBack() {
        onBackEvent.call()
    }

}