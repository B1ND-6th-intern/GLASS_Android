package kr.hs.dgsw.smartschool.glass_android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.MainViewModel
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivityMainBinding
import kr.hs.dgsw.smartschool.glass_android.view.fragment.HomeFragment
import kr.hs.dgsw.smartschool.glass_android.view.fragment.ProfileFragment
import kr.hs.dgsw.smartschool.glass_android.view.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    private val homeFragment by lazy { HomeFragment() }
    private val searchFragment by lazy { SearchFragment() }
    private val profileFragment by lazy { ProfileFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        initNavigationBar()
    }


    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()


    }

    private fun initNavigationBar() {
         binding.bnvMain.run {
             setOnNavigationItemSelectedListener {
                 when(it.itemId) {
                     R.id.main_home -> {
                         changeFragment(homeFragment)
                     }
                     R.id.main_search -> {
                         changeFragment(searchFragment)
                     }
                     R.id.main_profile -> {
                         changeFragment(profileFragment)
                     }
                 }
                 true
             }
             selectedItemId = R.id.main_home
         }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fm_container, fragment)
            .commit()
    }
}