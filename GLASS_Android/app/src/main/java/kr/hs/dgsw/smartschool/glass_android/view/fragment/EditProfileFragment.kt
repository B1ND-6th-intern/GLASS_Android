package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentEditProfileBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.EditProfileViewModel

class EditProfileFragment : Fragment() {
    lateinit var binding: FragmentEditProfileBinding
    lateinit var editProfileViewModel: EditProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_profile,
            container,
            false
        )
        performViewModel()

        with(editProfileViewModel) {
            onBackProfileEvent.observe(this@EditProfileFragment, {
                findNavController().navigate(R.id.action_editProfileFragment_to_main_profile)
            })

            onEditCheckEvent.observe(this@EditProfileFragment, {
                findNavController().navigate(R.id.action_editProfileFragment_to_main_profile)
            })
        }

        return binding.root
    }

    private fun performViewModel() {
        editProfileViewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        binding.vm = editProfileViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}