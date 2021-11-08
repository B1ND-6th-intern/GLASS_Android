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
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentSearchBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.SearchViewModel

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var searchViewModel: SearchViewModel
    var id1: String = ""
    var id2: String = ""
    var id3: String = ""

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
            getPopularPost()

            popularList.observe(this@SearchFragment.viewLifecycleOwner, {

                var reimg1: String = "http://10.80.163.231:8080/uploads${it[0].imgs[0]}"
                var reimg2: String = "http://10.80.163.231:8080/uploads${it[1].imgs[0]}"
                var reimg3: String = "http://10.80.163.231:8080/uploads${it[2].imgs[0]}"

                id1 = it[0]._id
                id2 = it[1]._id
                id3 = it[2]._id

                Glide.with(binding.root)
                    .load(reimg1)
                    .error(R.drawable.ic_iv_noimage)
                    .fitCenter()
                    .into(binding.ivFirst)

                Glide.with(binding.root)
                    .load(reimg2)
                    .error(R.drawable.ic_iv_noimage)
                    .fitCenter()
                    .into(binding.ivSecond)

                Glide.with(binding.root)
                    .load(reimg3)
                    .error(R.drawable.ic_iv_noimage)
                    .fitCenter()
                    .into(binding.ivThird)
            })

            onSearchEvent.observe(this@SearchFragment, {
                findNavController().apply { navigate(R.id.action_main_search_to_realSearchFragment) }
            })

            onPopularDetailEvent.observe(this@SearchFragment, {

                when(clickPermission.value) {
                    1 -> {
                        val action = SearchFragmentDirections.actionMainSearchToDetailFragment(id1)
                        findNavController().navigate(action)
                    }
                    2 -> {
                        val action = SearchFragmentDirections.actionMainSearchToDetailFragment(id2)
                        findNavController().navigate(action)
                    }
                    3 -> {
                        val action = SearchFragmentDirections.actionMainSearchToDetailFragment(id3)
                        findNavController().navigate(action)
                    }
                }
            })

            message.observe(this@SearchFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
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