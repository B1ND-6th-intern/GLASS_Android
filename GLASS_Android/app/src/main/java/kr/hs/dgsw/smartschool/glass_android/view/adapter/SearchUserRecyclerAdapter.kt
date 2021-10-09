package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemSearchUserBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.SearchUser

class SearchUserRecyclerAdapter(val lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<SearchUserRecyclerAdapter.SearchUserViewHolder>() {

    var searchUserList: List<SearchUser> = ArrayList<SearchUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): SearchUserRecyclerAdapter.SearchUserViewHolder {
        return SearchUserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        holder.bind(searchUserList[position])
    }

    override fun getItemCount(): Int = searchUserList.size

    class SearchUserViewHolder(private val binding: ItemSearchUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchUser: SearchUser) {
            with(searchUser) {
                binding.tvName.text = name
                binding.tvIntroduce.text = introduce

                Glide.with(binding.root)
                    .load(profileImage)
                    .error(R.drawable.ic_img_profile)
                    .centerCrop()
                    .into(binding.ivSearchProfile)
            }
        }
    }
}