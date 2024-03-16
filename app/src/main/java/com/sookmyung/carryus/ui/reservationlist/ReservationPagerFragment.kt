package com.sookmyung.carryus.ui.reservationlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentReservationPagerBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.ui.reservationlist.detail.ReservationDetailActivity
import com.sookmyung.carryus.util.binding.BindingFragment

class ReservationPagerFragment : BindingFragment<FragmentReservationPagerBinding>(R.layout.fragment_reservation_pager) {

    private val viewModel: ReservationPagerViewModel by viewModels()

    var data = listOf(ReservationList(1,"shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약"),
        ReservationList(2,"shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약"),
        ReservationList(3,"shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약"))

    private val reservationShopAdapter by lazy {
        ReservationShopAdapter(ReservationListClickListener { clickedReservation ->
            viewModel.onReservationItemClick(clickedReservation)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = arguments?.getString("content")
        if(content != null){
            when(content){
//                ReservationStatus.ACCEPTED.name -> setEmptyView()
//                ReservationStatus.WAITING.name -> setEmptyView()
                else -> setReservationList()
            }

        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner) { reservationList ->
            reservationList?.let {
                openCreateBuilding(it)

                viewModel.onNavigationComplete()
            }
        }
    }

    private fun setReservationList(){
        binding.tvEmptyText.visibility = View.GONE
        binding.ivEmptyIcon.visibility = View.GONE

        binding.recyclerView.apply{
            adapter = reservationShopAdapter
            layoutManager = LinearLayoutManager(context)
        }
        reservationShopAdapter.submitList(data)
    }

    private fun setEmptyView(){
        binding.recyclerView.visibility = View.GONE
    }

    private fun openCreateBuilding(reservationList: ReservationList) {
        val intent = Intent(context, ReservationDetailActivity::class.java)
        intent.putExtra("reservation_id", reservationList.reservationId)
        startActivity(intent)
    }

}
