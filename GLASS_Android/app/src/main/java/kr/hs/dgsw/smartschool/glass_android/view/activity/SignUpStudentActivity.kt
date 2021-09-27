package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivitySignUpStudentBinding
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.SelectJobViewModel
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.SignUpStudentViewModel

class SignUpStudentActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpStudentBinding
    lateinit var signUpStudentViewModel : SignUpStudentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(signUpStudentViewModel) {
            onSignUpEvent.observe(this@SignUpStudentActivity, {
                if(binding.checkboxPrivateInfo.isChecked) {
                    val intent = Intent(this@SignUpStudentActivity, CheckEmailActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "개인정보를 수락해주세요!", Toast.LENGTH_SHORT).show()
                }
            })

            onBackSelect.observe(this@SignUpStudentActivity, {
                finish()
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_student)
        signUpStudentViewModel = ViewModelProvider(this).get(SignUpStudentViewModel::class.java)
        binding.vm = signUpStudentViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}