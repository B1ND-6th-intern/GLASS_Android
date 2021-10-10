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
        performViewModel()
        initRecycler()

        with(profileViewModel) {
            onEditProfileEvent.observe(this@ProfileFragment, {
                findNavController().navigate(R.id.action_main_profile_to_editProfileFragment)
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

    private fun initRecycler() {
        var profilePostList = ArrayList<ProfilePost>()
        val profilePostRecyclerAdapter = ProfilePostRecyclerAdapter(viewLifecycleOwner)
        binding.profilePostRecycler.adapter = profilePostRecyclerAdapter

        profilePostList.apply {
            add(ProfilePost("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg"))
            add(ProfilePost("https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg"))
            add(ProfilePost("https://images.chosun.com/resizer/HoGaPo0K-HNh_w9wmkUxpt404rc=/616x0/filters:focal(291x444:301x454)/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/XG2MW2H3ZRW5FHDVSOMF6FDT3E.jpg"))
            add(ProfilePost("https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg"))
            add(ProfilePost("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg"))
        }
        profilePostRecyclerAdapter.profilePostList = profilePostList
        profilePostRecyclerAdapter.notifyDataSetChanged()
    }
}