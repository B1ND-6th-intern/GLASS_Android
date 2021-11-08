package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()

        with(checkEmailViewModel) {

            onCheckEvent.observe(this@CheckEmailActivity, {
                val intent = Intent(this@CheckEmailActivity, LoginActivity::class.java)
                startActivity(intent)
            })

            onExceedCount.observe(this@CheckEmailActivity, {
                Toast.makeText(this@CheckEmailActivity, "재전송 횟수가 초과했습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CheckEmailActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            })

            message.observe(this@CheckEmailActivity, {
                Toast.makeText(applicationContext, "${message.value}", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onBackPressed() {
        val eventHandler =
            DialogInterface.OnClickListener { p0, p1 ->
                if (p1 == DialogInterface.BUTTON_POSITIVE) {
                    finish()
                }
            }
        android.app.AlertDialog.Builder(this).run {
            setTitle("뒤로가기")
            setMessage("뒤로 가신다면 현재 이메일을 다시 사용할 수 없습니다!")
            setPositiveButton("뒤로가기", eventHandler)
            setNegativeButton("취소", eventHandler)
            show()
        }
        //super.onBackPressed()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_email)
        checkEmailViewModel = ViewModelProvider(this).get(CheckEmailViewModel::class.java)
        binding.vm = checkEmailViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}