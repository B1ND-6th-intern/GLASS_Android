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
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentProfileBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.ProfilePost
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.ProfilePostRecyclerAdapter
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.EditProfileViewModel
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.ProfileViewModel

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
        (activity as? MainActivity)?.binding?.btnSetting?.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )
        val profilePostRecyclerAdapter = ProfilePostRecyclerAdapter(viewLifecycleOwner)
        binding.profilePostRecycler.adapter = profilePostRecyclerAdapter
        performViewModel()

        with(profileViewModel) {
            getProfile()

            onEditProfileEvent.observe(this@ProfileFragment, {
                findNavController().navigate(R.id.action_main_profile_to_editProfileFragment)
            })

            userInfo.observe(this@ProfileFragment.viewLifecycleOwner, {
                binding.tvProfileName.text = it.writings[0].owner.name
                binding.tvJob.text = it.writings[0].owner.grade.toString() + it.writings[0].owner.classNumber.toString() + it.writings[0].owner.stuNumber.toString()


//                homeRecyclerAdapter.recyclerPostList = it
//                homeRecyclerAdapter.notifyDataSetChanged()
                profilePostRecyclerAdapter.profilePostList = it.writings[0].imgs
                profilePostRecyclerAdapter.notifyDataSetChanged()
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.vm = profileViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}