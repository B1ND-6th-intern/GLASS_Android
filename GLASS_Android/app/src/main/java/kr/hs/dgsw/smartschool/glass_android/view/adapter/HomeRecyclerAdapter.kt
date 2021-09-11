package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemHomePostBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.Post

class HomeRecyclerAdapter(val lifecycleOwner: LifecycleOwner):
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>(){

    var recyclerPostList : List<Post> = ArrayList<Post>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecyclerAdapter.HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_post,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeRecyclerAdapter.HomeViewHolder, position: Int) {
        holder.bind(recyclerPostList[position])
    }

    override fun getItemCount(): Int = recyclerPostList.size

    inner class HomeViewHolder(private val binding: ItemHomePostBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            with(post) {
                binding.tvCountHeart.text = likeStatus + "개"
                binding.tvPostUserName.text = name

                Glide.with(binding.root)
                    .load(profileImage)
                    .error(R.drawable.ic_img_default_profile)
                    .centerCrop()
                    .into(binding.ivUserProfile)

            }
        }
    }
}