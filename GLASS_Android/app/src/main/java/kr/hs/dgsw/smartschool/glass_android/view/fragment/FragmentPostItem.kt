package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemHomePostBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.PostImg
import kr.hs.dgsw.smartschool.glass_android.view.adapter.PostedImgAdapter

class FragmentPostItem : Fragment() {
    lateinit var binding: ItemHomePostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ItemHomePostBinding> (
            inflater,
            R.layout.fragment_post_item,
            container,
            false
        )

        initViewPager()

        return binding.root
    }

    private fun initViewPager() {
        var postImgList = ArrayList<PostImg>()
        val postedImgAdapter = PostedImgAdapter(viewLifecycleOwner)
        binding.viewPagerPost.adapter = postedImgAdapter

        postImgList.apply {
            add(PostImg("https://www.google.com/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%82%AC%EC%A7%84&sxsrf=AOaemvLA9deCEjNJk2Mq59vUOvkKCDmbFw:1631520651108&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi87-iGwPvyAhUWxosBHUldD5wQ_AUoAXoECAEQAw&biw=1920&bih=937#imgrc=y83bG29kGyBlkM"))
            add(PostImg("https://www.google.com/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%82%AC%EC%A7%84&sxsrf=AOaemvLA9deCEjNJk2Mq59vUOvkKCDmbFw:1631520651108&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi87-iGwPvyAhUWxosBHUldD5wQ_AUoAXoECAEQAw&biw=1920&bih=937#imgrc=Bw5Q30o6I3A_mM"))
            add(PostImg("https://www.google.com/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%82%AC%EC%A7%84&sxsrf=AOaemvLA9deCEjNJk2Mq59vUOvkKCDmbFw:1631520651108&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi87-iGwPvyAhUWxosBHUldD5wQ_AUoAXoECAEQAw&biw=1920&bih=937#imgrc=sXakFwq8jZOTTM"))
        }
        postedImgAdapter.postImgList = postImgList as ArrayList<PostImg>
        postedImgAdapter.notifyDataSetChanged()
    }

}