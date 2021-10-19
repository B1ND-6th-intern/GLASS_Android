package kr.hs.dgsw.smartschool.glass_android.network.response

data class LoginResponse(
    val status: Int,
    val message: String,
    val token: String
)
