package kr.hs.dgsw.smartschool.glass_android.network.request

data class SecondPostingRequest(
    val text : String,
    val hashtags: List<String>,
    val imgs: List<String>,
)
