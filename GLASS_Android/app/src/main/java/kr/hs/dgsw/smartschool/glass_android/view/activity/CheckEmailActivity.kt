package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivityCheckEmailBinding
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.CheckEmailViewModel

class CheckEmailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckEmailBinding
    lateinit var checkEmailViewModel: CheckEmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(checkEmailViewModel) {
            onBackSignUpEvent.observe(this@CheckEmailActivity, {
                finish()
            })

            onCheckEvent.observe(this@CheckEmailActivity, {
                val intent = Intent(this@CheckEmailActivity, LoginActivity::class.java)
                startActivity(intent)
            })
            onResendEmailEvent.observe(this@CheckEmailActivity, {
                Toast.makeText(this@CheckEmailActivity, "재전송 되었습니다!\n횟수는 총 $sendCount 회 남았습니다", Toast.LENGTH_SHORT).show()
            })
            onExceedCount.observe(this@CheckEmailActivity, {
                Toast.makeText(this@CheckEmailActivity, "재전송 횟수가 초과했습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CheckEmailActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_email)
        checkEmailViewModel = ViewModelProvider(this).get(CheckEmailViewModel::class.java)
        binding.vm = checkEmailViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}