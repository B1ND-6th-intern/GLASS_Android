package kr.hs.dgsw.smartschool.glass_android.network.response

data class HomeResponse(
    val writings: List<Writings>
)

data class Writings(
    val hashtags: List<String>,
    val imgs: List<String>,
    val text: String,
    val _id: String,
    val comments: List<String>,
    val owner: Owner,
    val isLike: Boolean,
    val likeCount: Int
)

data class Owner(
    val name: String,
    val avatar: String,
    val stuNumber: Int,
    val classNumber: Int,
    val grade: Int,
    val permission: Int
)

