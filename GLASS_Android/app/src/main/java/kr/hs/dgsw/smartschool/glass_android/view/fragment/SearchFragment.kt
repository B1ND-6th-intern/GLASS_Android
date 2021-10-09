package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.FragmentSearchBinding
import kr.hs.dgsw.smartschool.glass_android.network.model.PopularPost
import kr.hs.dgsw.smartschool.glass_android.view.activity.MainActivity
import kr.hs.dgsw.smartschool.glass_android.view.adapter.PopularPostRecyclerAdapter

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        initRecycler()
        return binding.root
    }

    private fun initRecycler() {
        val popularPostList = ArrayList<PopularPost>()
        val popularPostRecyclerAdapter = PopularPostRecyclerAdapter(viewLifecycleOwner)
        binding.popularPostRecycler.adapter = popularPostRecyclerAdapter

        popularPostList.apply {
            add(PopularPost("https://dimg.donga.com/wps/NEWS/IMAGE/2021/01/17/104953245.2.jpg", "현재 기숙사 상태..", "https://newsimg.hankookilbo.com/cms/articlerelease/2021/06/01/373cd776-11e3-4bc3-8e60-0963e82e938a.jpg", "1320", "최민재", "사감 신고, 사감"))
            add(PopularPost("https://img.hankyung.com/photo/201807/01.17324227.1.jpg", "하 이미지 넣기 힘들다", "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/7mo5/image/RhMj77_UZ1G9smD_INrbLKRVVoc.jpg", "교직원","전용길", "요즘 학생들, 꼰대"))
            add(PopularPost("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "더미 데이터가 너무 많아", "https://upload3.inven.co.kr/upload/2021/01/19/bbs/i14096652616.jpg", "1234", "임동현", "개발, 귀찮네"))
            add(PopularPost("난 모르겠다..", "바인드 인턴 안드 1명 위험", "https://img2.sbs.co.kr/img/sbs_cms/WE/2019/08/09/WE97496996_ori.jpg", "학부모","김씨준호", "안드, 위험, 긴급, 1명 더 구해요"))
            add(PopularPost("https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg", "대소고 축제의 한 순간", "https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg", "교직원", "김경호", "축제, 파티, 해커톤, 전시"))
            add(PopularPost("https://img2.sbs.co.kr/img/sbs_cms/WE/2019/08/09/WE97496996_ori.jpg", "대소고 광소고를 이기다", "난 이미지", "1245","기준", "?"))
            add(PopularPost("https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg", "경태 준성 열애설 터지다", "https://news.imaeil.com/inc/photos/2020/08/31/2020083115381755161_l.jpg", "1319", "이경태", "히힣, 준성아 사랑해, 난 턱이 길어"))
            add(PopularPost("https://upload3.inven.co.kr/upload/2021/01/19/bbs/i14096652616.jpg", "하..", "https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "1320", "최민재", "ㅋㅋㅋㅋ"))
            add(PopularPost("https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/7mo5/image/RhMj77_UZ1G9smD_INrbLKRVVoc.jpg", "히히히히히히힣", "https://img.hankyung.com/photo/201807/01.17324227.1.jpg", "학부모","이이잉", "아침밥은, 먹죠?"))
        }
        popularPostRecyclerAdapter.popularPostList = popularPostList
        popularPostRecyclerAdapter.notifyDataSetChanged()
    }
}