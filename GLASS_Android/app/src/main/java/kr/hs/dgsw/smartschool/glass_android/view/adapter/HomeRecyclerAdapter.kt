package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentPostItemBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.Post
import kr.hs.dgsw.smartschool.glass_android.network.model.PostImg
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings

class HomeRecyclerAdapter(val lifecycleOwner: LifecycleOwner):
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>(){

    var recyclerPostList : List<Writings> = ArrayList<Writings>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fragment_post_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(recyclerPostList[position])
    }

    override fun getItemCount(): Int = recyclerPostList.size

    class HomeViewHolder(private val binding: FragmentPostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(writings: Writings) {
            with(writings) {
                binding.tvCountHeart.text = likeCount.toString() + "개"
                binding.tvPostUserName.text = name
                binding.tvPostContent.text = text


                Glide.with(binding.root)
                    .load(profileImage)
                    .error(R.drawable.ic_img_profile)
                    .centerCrop()
                    .into(binding.ivUserProfile)

                var postImgList: ArrayList<PostImg> = ArrayList()

                postImgList.apply {
                    add(PostImg("https://img2.sbs.co.kr/img/sbs_cms/WE/2019/08/09/WE97496996_ori.jpg"))
                    add(PostImg("https://images.chosun.com/resizer/HoGaPo0K-HNh_w9wmkUxpt404rc=/616x0/filters:focal(291x444:301x454)/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/XG2MW2H3ZRW5FHDVSOMF6FDT3E.jpg"))
                    add(PostImg("https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/7mo5/image/RhMj77_UZ1G9smD_INrbLKRVVoc.jpg"))
                    add(PostImg("https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg"))
                    add(PostImg("https://upload3.inven.co.kr/upload/2021/01/19/bbs/i14096652616.jpg"))
                }
                var adapter = PostedImgAdapter(postImgList)
                binding.viewPagerPost.orientation= ViewPager2.ORIENTATION_HORIZONTAL
                binding.viewPagerPost.adapter = adapter
                binding.indicatorPost.setViewPager2(binding.viewPagerPost)
            }

        }
    }
}