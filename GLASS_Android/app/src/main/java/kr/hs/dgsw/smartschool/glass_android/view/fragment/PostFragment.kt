package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
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
import okhttp3.MultipartBody
import java.io.File

class PostFragment : Fragment() {
    lateinit var binding: FragmentPostBinding
    lateinit var postViewModel: PostViewModel

    // clipData가 저장될 list
    var list = ArrayList<PostingImg>()
    val multiImageAdapter by lazy { MultiImageAdapter(viewLifecycleOwner) }

    // 화면에 액션바와 네비게이션 바 제거
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
                var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                startActivityForResult(intent, 200)
            })

            // 서버에 값 보내는 기능 만들기
            onPostEvent.observe(this@PostFragment, {
                findNavController().navigate(R.id.action_postFragment_to_main_home)
            })

            onErrorEvent.observe(this@PostFragment, {
                Toast.makeText(context, "${error.value}", Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        list.clear()

        if (resultCode == Activity.RESULT_OK && requestCode == 200) {

            var images = java.util.ArrayList<MultipartBody.Part>()
            if (data?.clipData != null) {
                val count = data.clipData!!.itemCount

                if (count > 10) {
                    Toast.makeText(context, "사진은 최대 10장 입니다!", Toast.LENGTH_SHORT).show()
                    return
                }
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    postViewModel.images.value?.add( File(getRealPathFromURI(imageUri).path))
                    list.apply { add(PostingImg(imageUri)) }
                }
            } else {
                data?.data?.let { uri ->
                    val imageUri: Uri? = data.data
                    if (imageUri != null) {
                        postViewModel.images.value?.add( File(getRealPathFromURI(imageUri).path))
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

    private fun getRealPathFromURI(uri: Uri): Uri {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context?.contentResolver?.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return Uri.fromFile(File(picturePath ?: ""))
    }
}