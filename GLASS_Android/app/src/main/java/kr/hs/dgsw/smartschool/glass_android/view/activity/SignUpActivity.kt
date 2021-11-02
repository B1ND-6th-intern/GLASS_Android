package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        var permissions = intent.getIntExtra("Permission", -1)

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

            permission = permissions
            if(permission == 0) {
                binding.editClass.visibility = View.VISIBLE
                binding.editGrade.visibility = View.VISIBLE
                binding.editNumber.visibility = View.VISIBLE
            } else if (permission == 1 || permission == 2) {
                binding.editClass.visibility = View.GONE
                binding.editGrade.visibility = View.GONE
                binding.editNumber.visibility = View.GONE
            }

            onBackSelectEvent.observe(this@SignUpActivity, {
                finish()
            })

            onEmptyEvent.observe(this@SignUpActivity, {
                Toast.makeText(this@SignUpActivity, "칸이 비었어요!", Toast.LENGTH_SHORT).show()
            })

            message.observe(this@SignUpActivity, {
                Toast.makeText(this@SignUpActivity, "$it", Toast.LENGTH_SHORT).show()
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