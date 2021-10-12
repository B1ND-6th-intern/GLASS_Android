package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemPostImageBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.PostingImg

class MultiImageAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<MultiImageAdapter.PostViewHolder>(){

    var items : List<PostingImg> = ArrayList<PostingImg>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): MultiImageAdapter.PostViewHolder {
        return PostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class PostViewHolder(private val binding: ItemPostImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostingImg) {
            with(item) {
                Glide.with(binding.root)
                    .load(exImages)
                    .error(R.drawable.ic_iv_noimage)
                    .centerCrop()
                    .into(binding.ivExImage)

            }

        }
    }

}