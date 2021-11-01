package kr.hs.dgsw.smartschool.glass_android.network.response

data class ProfileResponse(
    val user: User
)

data class User(
    val name: String,
    val grade: Int,
    val classNumber: Int,
    val stuNumber: Int,
    val introduction: String,
    val writings: List<ProfileWriting>,
    val avatar: String,
    val permission: Int
)

data class ProfileWriting(
    val hashtags: List<String>,
    val imgs: List<String>,
    val text: String,
    val _id: String,
    var isLike: Boolean,
    val likeCount: Int
)