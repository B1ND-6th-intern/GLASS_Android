package kr.hs.dgsw.smartschool.glass_android.network.request

data class CommentUploadRequest (
    val text : String,
    val writingId: String
)