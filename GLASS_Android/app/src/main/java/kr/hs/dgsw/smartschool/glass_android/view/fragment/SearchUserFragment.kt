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
import kr.hs.dgsw.smartschool.glass_android.network.model.SearchUser
import kr.hs.dgsw.smartschool.glass_android.view.adapter.SearchUserRecyclerAdapter
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

        initRecycler()

        binding.editSearchUser.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Toast.makeText(context, "사용자 검색입니다!", Toast.LENGTH_SHORT).show()
                true
            } else {
                false
            }
        }
        return binding.root
    }

    private fun initRecycler() {
        val searchUserList = ArrayList<SearchUser>()
        val searchUserRecyclerAdapter = SearchUserRecyclerAdapter(viewLifecycleOwner)
        binding.searchUserRecycler.adapter = searchUserRecyclerAdapter

        searchUserList.apply {
            add(SearchUser("https://dimg.donga.com/wps/NEWS/IMAGE/2021/01/17/104953245.2.jpg", "1320 최민재", "개발 예정입니다ㅠ"))
            //add(SearchUser("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "교직원 이광남", "턱이 정말 깁니다."))
            //add(SearchUser("https://img.hankyung.com/photo/201807/01.17324227.1.jpg", "학부모 우준성", "경태 조련사입니다"))
        }
        searchUserRecyclerAdapter.searchUserList = searchUserList
        searchUserRecyclerAdapter.notifyDataSetChanged()
    }
}