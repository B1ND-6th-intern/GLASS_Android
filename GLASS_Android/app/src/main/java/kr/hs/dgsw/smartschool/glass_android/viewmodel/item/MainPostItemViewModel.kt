package kr.hs.dgsw.smartschool.glass_android.viewmodel.item

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings

class MainPostItemViewModel(val writings: Writings) : ViewModel() {

    val onHeartEvent = SingleLiveEvent<String>()
    val onCommentEvent = SingleLiveEvent<String>()

    fun onClickBtnHeart() {
        onHeartEvent.value = writings._id
    }

    fun onClickComment() {
        onCommentEvent.value = writings._id
    }
}
