package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivityLoginBinding
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    companion object {
        const val TOKEN_PREFERENCE = "TOKEN_PREFERENCES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(loginViewModel) {
            onLoginEvent.observe(this@LoginActivity, {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
            })

            token.observe(this@LoginActivity, {
                val sharedPref = this@LoginActivity.getSharedPreferences(TOKEN_PREFERENCE, Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("token", it)
                    apply()
                }
            })


            onSignUpEvent.observe(this@LoginActivity, {
                val intent = Intent(this@LoginActivity, SelectJobActivity::class.java)
                startActivity(intent)
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}