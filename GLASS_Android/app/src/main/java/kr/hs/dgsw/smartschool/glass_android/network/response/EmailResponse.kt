package kr.hs.dgsw.smartschool.glass_android.network.response

data class EmailResponse(
    // 이메일 인증 보류
    val sendCount : Int,
    val message : String
)
