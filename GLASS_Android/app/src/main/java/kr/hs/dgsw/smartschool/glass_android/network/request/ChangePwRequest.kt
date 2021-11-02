package kr.hs.dgsw.smartschool.glass_android.network.request

data class ChangePwRequest(
    val oldPassword : String,
    val newPassword : String,
    val newPasswordConfirmation : String
)
