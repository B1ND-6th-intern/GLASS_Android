package kr.hs.dgsw.smartschool.glass_android.network

import com.google.gson.GsonBuilder
import kr.hs.dgsw.smartschool.glass_android.network.api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient{
    private const val BASE_URL = "http://10.80.162.123:8080/"     // 준호
    //private const val BASE_URL = "http://10.80.163.231:8080/"       // 준성

    val loginInterface: Login
    val signUpInterface: SignUp
    val confirmInterface: Confirm
    val postingInterface: Posting
    val homeInterface: Home
    val detailInterface: Detail
    val commentUploadInterface : CommentUpload
    val popularPostInterface: PopularPost
    val myProfileInterface: MyProfile
    val profileInterface: Profile
    val likeInterface : Like

    init {
        val gson = GsonBuilder().setLenient().create()
        val intercepter = HttpLoggingInterceptor()
        intercepter.level = HttpLoggingInterceptor.Level.BODY

        val logger = OkHttpClient.Builder().addInterceptor(intercepter).addInterceptor(TokenInterceptor())
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()

        val instance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(logger)
            .build()

        loginInterface = instance.create(Login::class.java)
        signUpInterface = instance.create(SignUp::class.java)
        confirmInterface = instance.create(Confirm::class.java)
        postingInterface = instance.create(Posting::class.java)
        homeInterface = instance.create(Home::class.java)
        detailInterface = instance.create(Detail::class.java)
        commentUploadInterface = instance.create(CommentUpload::class.java)
        popularPostInterface = instance.create(PopularPost::class.java)
        myProfileInterface = instance.create(MyProfile::class.java)
        profileInterface = instance.create(Profile::class.java)
        likeInterface = instance.create(Like::class.java)
    }
}