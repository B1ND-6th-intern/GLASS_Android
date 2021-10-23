package kr.hs.dgsw.smartschool.glass_android.network.response

data class HomeResponse(
    val writings: List<Writings>
)

data class Writings(
    val hashtags: List<String>,
    val likeCount: Int,
    val imgs: List<String>,
    val text: String,
)

