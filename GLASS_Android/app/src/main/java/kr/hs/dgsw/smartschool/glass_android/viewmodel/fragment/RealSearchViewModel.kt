package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class RealSearchViewModel : ViewModel() {
    val onBackSearchEvent = SingleLiveEvent<Unit>()

    fun onClickBtnBackSearch() {
        onBackSearchEvent.call()
    }
}