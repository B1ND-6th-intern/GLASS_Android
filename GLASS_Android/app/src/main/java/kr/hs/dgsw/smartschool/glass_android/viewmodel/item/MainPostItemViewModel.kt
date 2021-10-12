package kr.hs.dgsw.smartschool.glass_android.viewmodel.item

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class MainPostItemViewModel : ViewModel() {
    val onHeartEvent = SingleLiveEvent<Unit>()
    val onCommentEvent = SingleLiveEvent<Unit>()

    fun onClickBtnHeart() {
        onHeartEvent.call()
    }

    fun onCommentEvent() {
        onCommentEvent.call()
    }
}
