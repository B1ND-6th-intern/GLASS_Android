package kr.hs.dgsw.smartschool.glass_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ItemCommentsRecyclerBinding
import kr.hs.dgsw.smartschool.glass_android.network.response.Comments

class CommentsRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsViewHolder>() {

    var recyclerCommentsList: ArrayList<Comments> = ArrayList<Comments>()

    class CommentsViewHolder(private val binding: ItemCommentsRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comments: Comments, lifecycleOwner: LifecycleOwner) {
            with(comments) {
                binding.tvCommentsName.text = owner.name
                binding.tvCommentsText.text = text
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentsViewHolder {
        return CommentsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comments_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(recyclerCommentsList[position], lifecycleOwner)
    }

    override fun getItemCount(): Int = recyclerCommentsList.size
}