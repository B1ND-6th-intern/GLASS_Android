package kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class SearchViewModel: ViewModel() {
    val onSearchEvent = SingleLiveEvent<Unit>()

    fun onClickBtnSearch() {
        onSearchEvent.call()
    }

}