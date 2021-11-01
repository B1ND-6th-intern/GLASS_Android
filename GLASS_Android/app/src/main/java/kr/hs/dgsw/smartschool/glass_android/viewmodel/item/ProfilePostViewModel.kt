package kr.hs.dgsw.smartschool.glass_android.viewmodel.item

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.response.ProfileWriting

class ProfilePostViewModel(val writings: ProfileWriting): ViewModel() {
    val onDetailEvent = SingleLiveEvent<String>()

    fun onClickDetail() {
        onDetailEvent.value = writings._id
    }
}