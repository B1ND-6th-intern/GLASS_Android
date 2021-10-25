package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentPostItemBinding
import kr.hs.dgsw.smartschool.glass_android.network.response.Writing
import kr.hs.dgsw.smartschool.glass_android.viewmodel.item.MainPostItemViewModel

class HomeRecyclerAdapter(val lifecycleOwner: LifecycleOwner):
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>(){

    var recyclerPostList : List<Writing> = ArrayList<Writing>()

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
        fun bind(writing: Writing) {
            with(writing) {

                val mainPostItemViewModel = MainPostItemViewModel()

                binding.tvCountHeart.text = likeCount.toString() + "개"
                binding.tvPostUserName.text = writing.owner.name
                binding.tvPostContent.text = text
                binding.tvPostName.text = writing.owner.name
                binding.tvPostUserNum.text = writing.owner.grade.toString() + writing.owner.classNumber.toString() + writing.owner.stuNumber.toString()
                for (i in 0 until hashtags.count())
                    binding.tvHashtags.text = hashtags[i] + " "

                Glide.with(binding.root)
                    .load(owner.avatar)
                    .error(R.drawable.ic_img_profile)
                    .centerCrop()
                    .into(binding.ivUserProfile)


//                var postImgsList: ArrayList<Writing> = ArrayList()
//                val postedImgAdapter = PostedImgAdapter(postImgsList)
//
//                binding.viewPagerPost.adapter = postedImgAdapter
//                // viewPager에 인디케이터 연결하기
//                binding.indicatorPost.setViewPager2(binding.viewPagerPost)

                with(mainPostItemViewModel) {

//                    postList.observe(this@HomeFragment.viewLifecycleOwner, {
//                        homeRecyclerAdapter.recyclerPostList = it
//
//                        homeRecyclerAdapter.notifyDataSetChanged()
//                    })
                }


            }

        }
    }
}