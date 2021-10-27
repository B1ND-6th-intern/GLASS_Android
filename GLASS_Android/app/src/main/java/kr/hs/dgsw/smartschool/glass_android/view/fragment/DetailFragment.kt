package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentDetailBinding
import kr.hs.dgsw.smartschool.glass_android.network.response.Writing
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.CommentsRecyclerAdapter
import kr.hs.dgsw.smartschool.glass_android.view.adapter.PostedImgAdapter
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.DetailViewModel


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var detailViewModel: DetailViewModel
    lateinit var writing: Writing

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
            getDetailPost()

            commentsList.observe(this@DetailFragment.viewLifecycleOwner, {
                commentsRecyclerAdapter.recyclerCommentsList = it
                commentsRecyclerAdapter.notifyDataSetChanged()
            })

            with(writing) {
                detailPost.observe(this@DetailFragment.viewLifecycleOwner, {
                    binding.tvCountHeart.text = likeCount.toString() + "개"
                    binding.tvPostContent.text = text
                    binding.tvPostName.text = writing.owner.name
                    binding.tvHashtags.text = ""
                    for (i in 0 until hashtags.count())
                        binding.tvHashtags.text =
                            binding.tvHashtags.text.toString() + hashtags[i] + "  "

                    binding.tvHashtags.text = "#" + binding.tvHashtags.text
                })
            }
            val postedImgAdapter = PostedImgAdapter(writing.imgs)
            binding.viewPagerPost.adapter = postedImgAdapter
            // viewPager에 인디케이터 연결하기
            binding.indicatorPost.setViewPager2(binding.viewPagerPost)

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