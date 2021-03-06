package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentSettingBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.LoginActivity
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.SettingViewModel

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    lateinit var settingViewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSettingBinding>(
            inflater,
            R.layout.fragment_setting,
            container,
            false
        )
        performViewModel()

        with(settingViewModel) {

            onBackProfileEvent.observe(this@SettingFragment, {
                findNavController().navigate(R.id.action_settingFragment_to_main_profile)
            })

            onLogoutEvent.observe(this@SettingFragment, {
                val loginPref = activity?.getSharedPreferences(LoginActivity.TOKEN_PREFERENCE, Activity.MODE_PRIVATE)
                with(loginPref?.edit()) {
                    this?.clear()
                    this?.commit()
                }

                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            })

            onChangePasswordEvent.observe(this@SettingFragment, {
                findNavController().navigate(R.id.action_settingFragment_to_changePwFragment)
            })
        }

        return binding.root
    }

    private fun performViewModel() {
        settingViewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
        binding.vm = settingViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}