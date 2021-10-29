package kr.hs.dgsw.smartschool.glass_android.network.response

data class ProfileResponse(
    val user: User
)

data class User(
    val writings: List<Writings>
)