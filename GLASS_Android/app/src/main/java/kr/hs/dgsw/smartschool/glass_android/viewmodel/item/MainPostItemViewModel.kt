package kr.hs.dgsw.smartschool.glass_android.viewmodel.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings

class MainPostItemViewModel : ViewModel() {
    val onHeartEvent = SingleLiveEvent<Unit>()
    val onCommentEvent = SingleLiveEvent<Unit>()

    // item 정보 받아오는걸
    val postImgList = MutableLiveData<List<Writings>>()

    fun onClickBtnHeart() {
        onHeartEvent.call()
    }

    fun onCommentEvent() {
        onCommentEvent.call()
    }
}
