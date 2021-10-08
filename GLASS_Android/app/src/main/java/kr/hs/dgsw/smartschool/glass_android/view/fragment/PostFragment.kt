package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentPostBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.PostingImg
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.MultiImageAdapter
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.PostViewModel

class PostFragment : Fragment() {
    lateinit var binding: FragmentPostBinding
    lateinit var postViewModel: PostViewModel

    var list = ArrayList<PostingImg>()
    val multiImageAdapter by lazy { MultiImageAdapter(viewLifecycleOwner) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPostBinding>(
            inflater,
            R.layout.fragment_post,
            container,
            false
        )

        performViewModel()
        binding.postImageRecycler.adapter = multiImageAdapter

        with(postViewModel) {
            onBackEvent.observe(this@PostFragment, {
                findNavController().navigate(R.id.action_postFragment_to_main_home)
            })

            onImageEvent.observe(this@PostFragment, {
                var intent = Intent(Intent.ACTION_PICK)
                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_GET_CONTENT

                startActivityForResult(intent, 200)
            })
        }
        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_OK && requestCode == 200) {
            list.clear()

            val unit = if (data?.clipData != null) {
                val count = data.clipData!!.itemCount
                if (count > 10) {
                    Toast.makeText(context, "사진은 최대 10장까지 선택 가능합니다!", Toast.LENGTH_LONG).show()
                    return
                }
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    list.apply { add(PostingImg(imageUri)) }
                }
            } else {
                data?.data?.let { uri ->
                    val imageUri: Uri? = data?.data
                    if (imageUri != null) {
                        list.apply { add(PostingImg(imageUri)) }
                    }
                }
            }

            multiImageAdapter.items = list
            multiImageAdapter.notifyDataSetChanged()
        }
    }

    private fun performViewModel() {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding.vm = postViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}