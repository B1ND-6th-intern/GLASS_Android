package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentPostItemBinding
import kr.hs.dgsw.smartschool.glass_android.extension.SingleLiveEvent
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings
import kr.hs.dgsw.smartschool.glass_android.viewmodel.item.MainPostItemViewModel

class HomeRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    var recyclerPostList: List<Writings> = ArrayList<Writings>()

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
        holder.bind(recyclerPostList[position], lifecycleOwner)
    }

    override fun getItemCount(): Int = recyclerPostList.size

    class HomeViewHolder(private val binding: FragmentPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(writings: Writings, lifecycleOwner: LifecycleOwner) {
            val viewModel = MainPostItemViewModel(writings)
            binding.vm = viewModel
            binding.lifecycleOwner = lifecycleOwner

            with(writings) {
                binding.tvCountHeart.text = likeCount.toString() + "개"
                binding.tvPostUserName.text = writings.owner.name
                binding.tvPostContent.text = text
                binding.tvPostName.text = writings.owner.name
                if (owner.permission == 0) {
                    binding.tvPostUserNum.text =
                        writings.owner.grade.toString() + writings.owner.classNumber.toString() + writings.owner.stuNumber.toString()
                } else if (owner.permission == 1) {
                    binding.tvPostUserNum.text = "학부모"
                } else if (owner.permission == 2) {
                    binding.tvPostUserNum.text = "선생님"
                }
                binding.tvHashtags.text = ""
                for (i in 0 until hashtags.count())
                    binding.tvHashtags.text =
                        binding.tvHashtags.text.toString() + hashtags[i] + "  "

                binding.tvHashtags.text = "#" + binding.tvHashtags.text

                var reAvartar: String = "http://api.glass.b1nd.com/uploads${owner.avatar}"

                Glide.with(binding.root)
                    .load(reAvartar)
                    .error(R.drawable.ic_img_profile)
                    .centerCrop()
                    .into(binding.ivUserProfile)

                Log.d("TESTTEST", "bind: $isLike")
                if (isLike) {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_blue)
                } else {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_w)
                }
            }

            val postedImgAdapter = PostedImgAdapter(writings.imgs)
            binding.viewPagerPost.adapter = postedImgAdapter
            // viewPager에 인디케이터 연결하기
            binding.indicatorPost.setViewPager2(binding.viewPagerPost)


            viewModel.onHeartEvent.observe(lifecycleOwner, {
                onHeartClick.value = writings._id

                if (!writings.isLike) {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_blue)
                    writings.isLike = true
                } else {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_w)
                    writings.isLike = false
                }
            })
            viewModel.onCommentEvent.observe(lifecycleOwner, {
                onCommentClick.value = writings._id
            })

        }
    }
    companion object {
        val onHeartClick = SingleLiveEvent<String>()
        val onCommentClick = SingleLiveEvent<String>()
    }
}