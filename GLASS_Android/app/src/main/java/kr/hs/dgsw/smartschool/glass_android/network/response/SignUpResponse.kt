package kr.hs.dgsw.smartschool.glass_android.network.response

data class SignUpResponse(
    val password : String,
    val password2 : String,
    val email : String,
    val name : String,
    val permission : Int,
    val isAgree : Boolean,
    val grade : Int,
    val classNumber : Int,
    val stuNumber : Int,
)
