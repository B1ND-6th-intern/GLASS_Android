package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemHomePostBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.Post
import kr.hs.dgsw.smartschool.glass_android.network.model.PostImg
import kr.hs.dgsw.smartschool.glass_android.view.adapter.HomeRecyclerAdapter
import kr.hs.dgsw.smartschool.glass_android.view.adapter.PostedImgAdapter
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
            add(Post("https://www.google.com/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%82%AC%EC%A7%84&sxsrf=AOaemvLA9deCEjNJk2Mq59vUOvkKCDmbFw:1631520651108&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi87-iGwPvyAhUWxosBHUldD5wQ_AUoAXoECAEQAw&biw=1920&bih=937#imgrc=sXakFwq8jZOTTM", "1320 최민재", "sdadfjslk", "1411"))
            add(Post("aasdfasdfa", "선생님 송준호", "asdfkljaslkfj", "25225"))
            add(Post("https://www.google.com/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%82%AC%EC%A7%84&sxsrf=AOaemvLA9deCEjNJk2Mq59vUOvkKCDmbFw:1631520651108&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi87-iGwPvyAhUWxosBHUldD5wQ_AUoAXoECAEQAw&biw=1920&bih=937#imgrc=sXakFwq8jZOTTM", "학부모 이경태", "sdadfsadfajslk", "0"))
            add(Post("asafj", "1421 손원", "sdadfsadfasdf", "14"))
            add(Post("https://www.google.com/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%82%AC%EC%A7%84&sxsrf=AOaemvLA9deCEjNJk2Mq59vUOvkKCDmbFw:1631520651108&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi87-iGwPvyAhUWxosBHUldD5wQ_AUoAXoECAEQAw&biw=1920&bih=937#imgrc=sXakFwq8jZOTTM", "1555 남궁경헌", "sdadfasdf", "256"))

        }
        homeRecyclerAdapter.recyclerPostList = postList as ArrayList<Post>
        homeRecyclerAdapter.notifyDataSetChanged()
    }

}