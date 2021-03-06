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
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.HomeRecyclerAdapter
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.HomeViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
        (activity as? MainActivity)?.binding?.btnSetting?.visibility = View.GONE
        (activity as? MainActivity)?.binding?.btnPosting?.visibility = View.VISIBLE
        when(MainActivity.permissionWorld) {
            0 -> {
                (activity as? MainActivity)?.binding?.btnPosting?.visibility = View.VISIBLE
            }
            1 -> {
                (activity as? MainActivity)?.binding?.btnPosting?.visibility = View.GONE
            }
            2 -> {
                (activity as? MainActivity)?.binding?.btnPosting?.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )


        performViewModel()
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            initRecycler()
//            return@setOnRefreshListener
//        }

        val homeRecyclerAdapter = HomeRecyclerAdapter(viewLifecycleOwner)
        binding.homeRecycler.adapter = homeRecyclerAdapter

        with(homeViewModel) {
            getHomePost()

            postList.observe(this@HomeFragment.viewLifecycleOwner, {
                homeRecyclerAdapter.recyclerPostList = it
                homeRecyclerAdapter.notifyDataSetChanged()
            })

            HomeRecyclerAdapter.onCommentClick.observe(this@HomeFragment, {
                val action = HomeFragmentDirections.actionMainHomeToDetailFragment(it)
                findNavController().navigate(action)
            })

            HomeRecyclerAdapter.onHeartClick.observe(this@HomeFragment, {
                id.value = it
                onClickLikeBtn()
            })

            message.observe(this@HomeFragment.viewLifecycleOwner, {
                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.vm = homeViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}