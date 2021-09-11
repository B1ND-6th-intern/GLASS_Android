package kr.hs.dgsw.smartschool.glass_android.viewmodel.item

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class MainPostItemViewModel : ViewModel() {
    val onLikeEvent = SingleLiveEvent<Unit>()
    val onCommentEvent = SingleLiveEvent<Unit>()

    fun onClickLike() {
        onLikeEvent.call()
    }

    fun onCommentEvent() {
        onCommentEvent.call()
    }
}
