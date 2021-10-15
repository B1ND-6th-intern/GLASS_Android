package kr.hs.dgsw.smartschool.glass_android.network.request

data class SecondPostingRequest(
    val text : String,
    val hashtags: String,
    val images: List<String>
)
