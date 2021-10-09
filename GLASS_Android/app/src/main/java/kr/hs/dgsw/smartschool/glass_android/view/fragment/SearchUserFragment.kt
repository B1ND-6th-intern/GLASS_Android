package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentSearchUserBinding
import kr.hs.dgsw.smartschool.glass_android.viewmodel.fragment.SearchUserViewModel

class SearchUserFragment : Fragment() {
    lateinit var binding: FragmentSearchUserBinding
    lateinit var searchUserViewModel: SearchUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_user,
            container,
            false
        )

        binding.editSearchUser.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Toast.makeText(context, "검색입니다!", Toast.LENGTH_SHORT).show()
                true
            } else {
                false
            }
        }
        return binding.root
    }
}