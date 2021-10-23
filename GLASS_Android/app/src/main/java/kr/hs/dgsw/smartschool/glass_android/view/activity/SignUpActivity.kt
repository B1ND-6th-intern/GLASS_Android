package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivitySignUpBinding
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpBinding
    lateinit var signUpViewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(signUpViewModel) {
            onSignUpEvent.observe(this@SignUpActivity, {
                if(binding.checkboxPrivateInfo.isChecked) {
                    val intent = Intent(this@SignUpActivity, CheckEmailActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@SignUpActivity, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignUpActivity, "개인정보를 수락해주세요!", Toast.LENGTH_SHORT).show()
                }
            })


            onEmailEvent.observe(this@SignUpActivity, {
                Toast.makeText(applicationContext, "재전송 횟수는 총 $sendCount 회 남았습니다", Toast.LENGTH_SHORT).show()
            })

            onBackSelectEvent.observe(this@SignUpActivity, {
                finish()
            })

            onEmptyEvent.observe(this@SignUpActivity, {
                Toast.makeText(this@SignUpActivity, "칸이 비었어요!", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.vm = signUpViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}