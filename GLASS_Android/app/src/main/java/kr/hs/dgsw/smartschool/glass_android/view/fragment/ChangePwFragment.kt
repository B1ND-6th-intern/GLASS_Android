package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentChangePwBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.LoginActivity
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.ChangePwViewModel

class ChangePwFragment : Fragment() {
    lateinit var binding: FragmentChangePwBinding
    lateinit var changePwViewModel: ChangePwViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentChangePwBinding>(
            inflater,
            R.layout.fragment_change_pw,
            container,
            false
        )

        performViewModel()

        with(changePwViewModel) {
            onBackEvent.observe(this@ChangePwFragment, {
                findNavController().navigate(R.id.action_changePwFragment_to_settingFragment)
            })

            onChangePwEvent.observe(this@ChangePwFragment, {
                Toast.makeText(context, "변경에 성공했습니다.\n다시 로그인 해주세요", Toast.LENGTH_SHORT).show()

                val pref = activity?.getSharedPreferences(LoginActivity.TOKEN_PREFERENCE, Activity.MODE_PRIVATE)
                with(pref?.edit()) {
                    this?.clear()
                    this?.commit()
                }

                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            })

            message.observe(this@ChangePwFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        changePwViewModel = ViewModelProvider(this).get(ChangePwViewModel::class.java)
        binding.vm = changePwViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}