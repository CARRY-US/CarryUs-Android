package com.sookmyung.carryus.ui.reservationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentReservationPagerBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReservationStatus
import com.sookmyung.carryus.util.binding.BindingFragment

class ReservationPagerFragment : BindingFragment<FragmentReservationPagerBinding>(R.layout.fragment_reservation_pager) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inflate the layout for this fragment

        val content = arguments?.getString("content")
        if(content != null){
            when(content){
                ReservationStatus.ACCEPTED.name -> setEmptyView()
                ReservationStatus.WAITING.name -> setEmptyView()
                else -> setReservationList()
            }

        }
    }

    private fun setReservationList(){
        binding.tvEmptyText.visibility = View.GONE
        binding.ivEmptyIcon.visibility = View.GONE

        var data = listOf(ReservationList("shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약"),
            ReservationList("shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약"),
            ReservationList("shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약"))

        binding.recyclerView.apply{
            adapter = ReservationShopAdapter(data)
            layoutManager = LinearLayoutManager(context)
        }
    }

    // 데이터가 없을 때 보여줄 화면
    private fun setEmptyView(){
        binding.recyclerView.visibility = View.GONE
    }

}
