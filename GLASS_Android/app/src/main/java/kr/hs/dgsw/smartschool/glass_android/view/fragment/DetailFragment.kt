package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentDetailBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.CommentsRecyclerAdapter
import kr.hs.dgsw.smartschool.glass_android.view.adapter.PostedImgAdapter
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.DetailViewModel


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var detailViewModel: DetailViewModel
    val id: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        performViewModel()

        // 댓글 리사이클러뷰 연결
        val commentsRecyclerAdapter = CommentsRecyclerAdapter(viewLifecycleOwner)
        binding.commentsRecycler.adapter = commentsRecyclerAdapter

        with(detailViewModel) {
            val _id = id.postId
            getDetailPost(_id)

            detailPost.observe(this@DetailFragment.viewLifecycleOwner, {
                binding.tvCountHeart.text = it.likeCount.toString() + "개"
                binding.tvPostContent.text = it.text
                binding.tvPostName.text = it.owner.name
                binding.tvHashtags.text = ""
                for (i in 0 until it.hashtags.count())
                    binding.tvHashtags.text =
                        binding.tvHashtags.text.toString() + it.hashtags[i] + "  "

                binding.tvHashtags.text = "#" + binding.tvHashtags.text

                val postedImgAdapter = PostedImgAdapter(it.imgs)
                binding.viewPagerPost.adapter = postedImgAdapter
                // viewPager에 인디케이터 연결하기
                binding.indicatorPost.setViewPager2(binding.viewPagerPost)

                commentsRecyclerAdapter.recyclerCommentsList = it.comments
                commentsRecyclerAdapter.notifyDataSetChanged()
            })

            commentsList.observe(this@DetailFragment.viewLifecycleOwner, {
                commentsRecyclerAdapter.recyclerCommentsList.add(it)
                commentsRecyclerAdapter.notifyDataSetChanged()
            })

            onUploadEvent.observe(this@DetailFragment, {
                binding.editSendComment.text = null
                val imm: InputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)

            })

            onEmptyCommentEvent.observe(this@DetailFragment, {
                Toast.makeText(context, "댓글을 입력해주세요", Toast.LENGTH_SHORT).show()
            })

            writingId.value = _id

            onBackEvent.observe(this@DetailFragment, {
                findNavController().navigate(R.id.action_detailFragment_to_main_home)
            })


        }
        return binding.root
    }

    private fun performViewModel() {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel = DetailViewModel()
        binding.vm = detailViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}