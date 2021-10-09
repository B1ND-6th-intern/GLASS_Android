package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemPopularPostBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.PopularPost

class PopularPostRecyclerAdapter(val lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<PopularPostRecyclerAdapter.PopularPostViewHolder>() {

    var popularPostList : List<PopularPost> = ArrayList<PopularPost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): PopularPostViewHolder {
        return PopularPostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_popular_post,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularPostViewHolder, position: Int) {
        holder.bind(popularPostList[position])
    }

    override fun getItemCount(): Int = popularPostList.size

    class PopularPostViewHolder(private val binding: ItemPopularPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popularPost : PopularPost) {
            with(popularPost) {
                binding.tvPopularHashtag.text = "#$hashTag"
                binding.tvPopularName.text = name
                binding.tvPopularTitle.text = mainTitle
                binding.tvPopularNum.text = num

                Glide.with(binding.root)
                    .load(mainImage)
                    .error(R.drawable.ic_iv_noimage)
                    .centerCrop()
                    .into(binding.ivPopularImage)

                Glide.with(binding.root)
                    .load(profileImage)
                    .error(R.drawable.ic_img_profile)
                    .centerCrop()
                    .into(binding.ivPopularProfile)
            }

        }
    }
}