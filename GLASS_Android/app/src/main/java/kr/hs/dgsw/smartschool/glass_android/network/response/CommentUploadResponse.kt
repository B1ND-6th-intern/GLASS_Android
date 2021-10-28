package kr.hs.dgsw.smartschool.glass_android.network.response

data class CommentUploadResponse(
    val status: Int,
    val message: String,
    val comment: Comments
)
