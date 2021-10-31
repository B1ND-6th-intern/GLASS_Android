package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.text.Layout
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemProfilePostBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.ProfilePost
import kr.hs.dgsw.smartschool.glass_android.network.model.User
import kr.hs.dgsw.smartschool.glass_android.network.response.Writings

class ProfilePostRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<ProfilePostRecyclerAdapter.ProfilePostViewHolder>() {

    var profilePostList: List<Writings> = ArrayList<Writings>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ProfilePostViewHolder {
        return ProfilePostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_profile_post,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfilePostViewHolder, position: Int) {
        holder.bind(profilePostList[position])
    }

    override fun getItemCount(): Int = profilePostList.size

    class ProfilePostViewHolder(private val binding: ItemProfilePostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(writings: Writings) {

            var reUrl: String = "http://10.80.162.123:8080/uploads${writings.imgs[0]}"


            Glide.with(binding.root)
                .load(reUrl)
                .error(R.drawable.ic_iv_noimage)
                .centerCrop()
                .into(binding.btnProfilePost)

        }
    }
}