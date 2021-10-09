package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentRealSearchBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.RealSearchViewModel

class RealSearchFragment : Fragment() {
    lateinit var binding: FragmentRealSearchBinding
    lateinit var realSearchViewModel: RealSearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentRealSearchBinding>(
            inflater,
            R.layout.fragment_real_search,
            container,
            false
        )
        performViewModel()

        with(realSearchViewModel) {
            onBackSearchEvent.observe(this@RealSearchFragment, {
                findNavController().apply { navigate(R.id.action_realSearchFragment_to_main_search) }
            })
        }

        binding.searchTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = childFragmentManager.beginTransaction()
                when (tab?.text) {
                    "사용자" -> {
                        transaction.replace(R.id.search_tap_content, SearchUserFragment())
                        binding.editSearch.hint = "사용자를 검색해주세요"
                    }
                    "태그" -> {
                        transaction.replace(R.id.search_tap_content, SearchTagFragment())
                        binding.editSearch.hint = "태그를 검색해주세요"
                    }
                }
                transaction.commit()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        return binding.root
    }

    private fun performViewModel() {
        realSearchViewModel = ViewModelProvider(this).get(RealSearchViewModel::class.java)
        binding.vm = realSearchViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}