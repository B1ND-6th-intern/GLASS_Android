package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentPostBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.PostViewModel

class PostFragment : Fragment() {
    lateinit var binding: FragmentPostBinding
    lateinit var postViewModel: PostViewModel

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

    private fun performViewModel() {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding.vm = postViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }


}