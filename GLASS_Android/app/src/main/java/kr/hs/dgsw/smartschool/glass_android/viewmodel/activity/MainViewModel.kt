package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val onPostEvent = SingleLiveEvent<Unit>()

//    val count = MutableLiveData<Int>(0)

    fun onClickBtnPost() {
        onPostEvent.call()
    }
}