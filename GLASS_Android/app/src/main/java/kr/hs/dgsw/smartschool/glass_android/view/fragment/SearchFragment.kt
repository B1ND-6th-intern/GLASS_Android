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
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentSearchBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.SearchViewModel

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var searchViewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setAppBarVisible(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        performViewModel()

        with(searchViewModel) {
            onSearchEvent.observe(this@SearchFragment, {
                findNavController().apply { navigate(R.id.action_main_search_to_realSearchFragment) }
            })
        }

        return binding.root
    }

    private fun performViewModel() {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.vm = searchViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}