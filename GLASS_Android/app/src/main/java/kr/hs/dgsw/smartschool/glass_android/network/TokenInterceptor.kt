package kr.hs.dgsw.smartschool.glass_android.network

import android.content.Context
import android.util.Log
import kr.hs.dgsw.smartschool.glass_android.view.activity.LoginActivity
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val context: Context = MyApp.instance
        val sharedPref = context.getSharedPreferences(LoginActivity.TOKEN_PREFERENCE, Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "") ?: ""
        Log.e("TESTTEST", "token $token")
        val request = chain.request().newBuilder()
            .header("authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}