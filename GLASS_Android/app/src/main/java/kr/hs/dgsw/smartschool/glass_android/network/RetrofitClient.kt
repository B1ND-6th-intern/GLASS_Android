package kr.hs.dgsw.smartschool.glass_android.network

import com.google.gson.GsonBuilder
import kr.hs.dgsw.smartschool.glass_android.network.api.Login
import kr.hs.dgsw.smartschool.glass_android.network.api.SignUp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    //    private const val BASE_URL = "http://10.80.162.123:8080/"
    val loginInterface: Login
    val signUpInterface: SignUp

    init {
        val gson = GsonBuilder().setLenient().create()
        val intercepter = HttpLoggingInterceptor()
        intercepter.level = HttpLoggingInterceptor.Level.BODY

        val logger = OkHttpClient.Builder().addInterceptor(intercepter)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        val instance = Retrofit.Builder()
            .baseUrl("https://glass.loca.lt/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(logger)
            .build()

        loginInterface = instance.create(Login::class.java)
        signUpInterface = instance.create(SignUp::class.java)
    }
}