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
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.HomeRecyclerAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
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

        binding.swipeRefreshLayout.setOnRefreshListener {
            initRecycler()
            return@setOnRefreshListener
        }

        initRecycler()
        return binding.root
    }

    private fun initRecycler() {

        var postList = ArrayList<Post>()
        val homeRecyclerAdapter = HomeRecyclerAdapter(viewLifecycleOwner)
        binding.homeRecycler.adapter = homeRecyclerAdapter

        postList.apply {
            add(Post("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "1320 최민재", "1411"))
            add(Post("aasdfasdfa", "선생님 송준호", "25225"))
            add(Post("https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg", "학부모 이경태", "0"))
            add(Post("asafj", "1421 손원", "14"))
            add(Post("https://images.chosun.com/resizer/HoGaPo0K-HNh_w9wmkUxpt404rc=/616x0/filters:focal(291x444:301x454)/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/XG2MW2H3ZRW5FHDVSOMF6FDT3E.jpg", "1555 남궁경헌", "256"))
        }
        homeRecyclerAdapter.recyclerPostList = postList as ArrayList<Post>
        homeRecyclerAdapter.notifyDataSetChanged()
    }
}