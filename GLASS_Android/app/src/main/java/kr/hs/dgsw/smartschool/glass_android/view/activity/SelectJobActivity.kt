package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivitySelectJobBinding
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.SelectJobViewModel

class SelectJobActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectJobBinding
    lateinit var selectJobViewModel: SelectJobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()

        with(selectJobViewModel) {
            onStudentEvent.observe(this@SelectJobActivity, {
                val intent = Intent(this@SelectJobActivity, SignUpActivity::class.java)
                intent.putExtra("Permission", 0)
                startActivity(intent)
            })

            onParentsEvent.observe(this@SelectJobActivity, {
                val intent = Intent(this@SelectJobActivity, SignUpActivity::class.java)
                intent.putExtra("Permission", 1)
                startActivity(intent)
            })

            onTeacherEvent.observe(this@SelectJobActivity, {
                val intent = Intent(this@SelectJobActivity, SignUpActivity::class.java)
                intent.putExtra("Permission", 2)
                startActivity(intent)
            })

            onBackLoginEvent.observe(this@SelectJobActivity, {
                finish()
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_job)
        selectJobViewModel = ViewModelProvider(this).get(SelectJobViewModel::class.java)
        binding.vm = selectJobViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}