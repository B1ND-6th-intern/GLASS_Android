package kr.hs.dgsw.smartschool.glass_android.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent

class SelectJobViewModel : ViewModel() {
    val onStudentEvent = SingleLiveEvent<Unit>()
    val onTeacherEvent = SingleLiveEvent<Unit>()
    val onParentsEvent = SingleLiveEvent<Unit>()
    val onBackLoginEvent = SingleLiveEvent<Unit>()

    fun onClickStudent() {
        onStudentEvent.call()
    }
    fun onClickTeacher() {
        onTeacherEvent.call()
    }
    fun onClickParents() {
        onParentsEvent.call()
    }
    fun onClickBackLogin() {
        onBackLoginEvent.call()
    }

}