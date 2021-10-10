package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentEditProfileBinding
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.EditProfileViewModel

class EditProfileFragment : Fragment() {
    lateinit var binding: FragmentEditProfileBinding
    lateinit var editProfileViewModel: EditProfileViewModel

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
            R.layout.fragment_edit_profile,
            container,
            false
        )
        performViewModel()

        with(editProfileViewModel) {
            onBackProfileEvent.observe(this@EditProfileFragment, {
                findNavController().navigate(R.id.action_editProfileFragment_to_main_profile)
            })

            onEditCheckEvent.observe(this@EditProfileFragment, {
                findNavController().navigate(R.id.action_editProfileFragment_to_main_profile)
            })

            onChangeProfileImageEvent.observe(this@EditProfileFragment, {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, 10)
            })
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val imageUri: android.net.Uri? = data.data
                if (imageUri != null) {
                    Glide.with(binding.root)
                        .load(imageUri)
                        .error(R.drawable.ic_img_profile)
                        .centerCrop()
                        .into(binding.ivEditProfile)
                }
            }
        }
    }

    private fun performViewModel() {
        editProfileViewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        binding.vm = editProfileViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}