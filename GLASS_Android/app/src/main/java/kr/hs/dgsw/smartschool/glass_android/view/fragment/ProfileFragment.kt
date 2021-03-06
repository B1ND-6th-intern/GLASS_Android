package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentProfileBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.ProfilePost
import kr.hs.dgsw.smartschool.glass_android.network.response.User
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.ProfilePostRecyclerAdapter
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.EditProfileViewModel
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.ProfileViewModel

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

    companion object {
        var UNITE_NAME : String = ""
        var UNITE_INTRO : String = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
        (activity as? MainActivity)?.binding?.btnSetting?.visibility = View.VISIBLE
        (activity as? MainActivity)?.binding?.btnPosting?.visibility = View.GONE
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

            ProfilePostRecyclerAdapter.onDetailClick.observe(this@ProfileFragment, {
                val action = ProfileFragmentDirections.actionMainProfileToDetailFragment(it)
                findNavController().navigate(action)
            })

            onEditProfileEvent.observe(this@ProfileFragment, {
                findNavController().navigate(R.id.action_main_profile_to_editProfileFragment)
            })

            userInfo.observe(this@ProfileFragment.viewLifecycleOwner, {
                binding.tvProfileName.text = it.name
                UNITE_NAME = it.name
                binding.tvIntroduce.text = it.introduction
                UNITE_INTRO = it.introduction

                var reUrl: String = "http://api.glass.b1nd.com/uploads${it.avatar}"

                Glide.with(binding.root)
                    .load(reUrl)
                    .error(R.drawable.ic_img_profile)
                    .centerCrop()
                    .into(binding.ivProfile)

                when(it.permission) {
                    0 -> {
                        // ??????
                        binding.tvJob.text = it.grade.toString() + it.classNumber.toString() + it.stuNumber.toString()
                    }
                    1 -> {
                        // ?????????
                        binding.tvJob.text = "?????????"
                    }
                    2 -> {
                        // ?????????
                        binding.tvJob.text = "?????????"
                    }
                }

                profilePostRecyclerAdapter.profilePostList = it.writings
                profilePostRecyclerAdapter.notifyDataSetChanged()
            })

            message.observe(this@ProfileFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
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