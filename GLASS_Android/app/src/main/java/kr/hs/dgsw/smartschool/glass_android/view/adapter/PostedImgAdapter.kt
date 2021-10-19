package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemHomePostedImgBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.PostImg

class PostedImgAdapter(private val postImgList: ArrayList<PostImg>):
  RecyclerView.Adapter<PostedImgAdapter.PagerViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): PostedImgAdapter.PagerViewHolder {
    return PagerViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_home_posted_img,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: PostedImgAdapter.PagerViewHolder, position: Int) {
    holder.bind(postImgList[position])
  }

  override fun getItemCount(): Int = postImgList.size

  inner class PagerViewHolder(private val binding: ItemHomePostedImgBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(postImg: PostImg) {
      with(postImg) {
        Glide.with(binding.root)
          .load(contentImg)
          .error(R.drawable.ic_iv_noimage)
          .centerCrop()
          .into(binding.imgPostContent)
      }
    }
  }
}