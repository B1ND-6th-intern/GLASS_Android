package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.Post
import kr.hs.dgsw.smartschool.glass_android.view.adapter.HomeRecyclerAdapter
import retrofit2.http.POST

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        initRecycler()

        return binding.root
    }

    private fun initRecycler() {

        var postList = ArrayList<Post>()
        val homeRecyclerAdapter = HomeRecyclerAdapter(viewLifecycleOwner)
        binding.homeRecycler.adapter = homeRecyclerAdapter

        postList.apply {
            add(Post("asdjfdafj", "1320 최민재", "sdadfjslk", "1411"))
            add(Post("aasdfasdfa", "선생님 송준호", "asdfkljaslkfj", "25225"))
            add(Post("asdjfdasdfafj", "학부모 이경태", "sdadfsadfajslk", "0"))
            add(Post("asafj", "1421 손원", "sdadfsadfasdf", "14"))
            add(Post("asafjasdf", "1555 남궁경헌", "sdadfasdf", "256"))

        }
        homeRecyclerAdapter.recyclerPostList = postList as ArrayList<Post>
        homeRecyclerAdapter.notifyDataSetChanged()
    }

}