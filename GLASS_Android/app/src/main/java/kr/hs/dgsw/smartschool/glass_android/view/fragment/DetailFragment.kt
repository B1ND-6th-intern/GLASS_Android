package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var statusHeart: Boolean = false
    val id: DetailFragmentArgs by navArgs()

    val menuItems = arrayOf<String>("삭제하기", "수정하기(개발 예정)")

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
        binding.commentsRecycler.scrollToPosition(commentsRecyclerAdapter.itemCount - 1)

        with(detailViewModel) {
            val statusPost: Int = statusDeletePost.value?.toInt() ?: -1
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

                if (it.isOwner) {
                    binding.btnPostMenu.visibility = View.VISIBLE
                } else {
                    binding.btnPostMenu.visibility = View.GONE
                }
                statusHeart = it.isLike
                if (it.isLike) {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_blue)
                } else {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_w)
                }

                message.observe(this@DetailFragment.viewLifecycleOwner, {
                    Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
                })
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

            // 메뉴 화면 AlertDialog 2개
            onMenuEvent.observe(this@DetailFragment, {
                AlertDialog.Builder(context).run {
                    setTitle("게시물 메뉴")
                    setItems(menuItems
                    ) { dialog, which ->
                        if (which == 0) {
                            val eventHandler =
                                DialogInterface.OnClickListener { p0, p1 ->
                                    if (p1 == DialogInterface.BUTTON_POSITIVE) {
                                        Toast.makeText(context, "게시물을 삭제합니다", Toast.LENGTH_SHORT).show()
                                        deletePost(_id)
                                        if (statusPost == 1) {
                                            Toast.makeText(context, "삭제에 성공했습니다", Toast.LENGTH_SHORT).show()
                                        } else if (statusPost == 0) {
                                            Toast.makeText(context, "삭제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                                        }
                                        findNavController().navigate(R.id.action_detailFragment_to_main_home)
                                    } else if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                                        Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            AlertDialog.Builder(context).run {
                                setTitle("게시물 삭제하기")
                                setMessage("정말로 삭제하시겠습니까?")
                                setPositiveButton("네", eventHandler)
                                setNegativeButton("아니오", eventHandler)
                                show()
                            }

                        } else if (which == 1) {
                            Toast.makeText(context, "개발 예정입니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                    setPositiveButton("닫기", null)
                    show()
                }
            })

            onHeartEvent.observe(this@DetailFragment, {
                onClickLikeBtn(_id)
                if (statusHeart) {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_w)
                } else {
                    binding.btnPostHeart.setBackgroundResource(R.drawable.btn_heart_blue)
                }
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