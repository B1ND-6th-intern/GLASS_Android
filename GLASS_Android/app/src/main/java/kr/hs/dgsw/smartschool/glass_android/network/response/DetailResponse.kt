package kr.hs.dgsw.smartschool.glass_android.network.response

data class DetailResponse(
    val writing: Writing
)

data class Writing(
    val text : String,
    val hashtags: List<String>,
    val likeCount : Int,
    val owner: Owner,
    val comments: List<Comments>,
    val imgs: List<String>,
    val isLike: Boolean,
    val isOwner: Boolean
)

data class Comments(
    val owner: Owner,
    val text: String
)
