package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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