package kr.hs.dgsw.smartschool.glass_android.network

import com.google.gson.GsonBuilder
import kr.hs.dgsw.smartschool.glass_android.network.api.Login
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.80.162.123:8080/"
    private val gson = GsonBuilder().setLenient().create()

    val instance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val loginInterface = instance.create(Login::class.java)

}