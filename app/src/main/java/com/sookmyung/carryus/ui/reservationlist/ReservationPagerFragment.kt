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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        binding.viewModel = viewModel

        setContent()
        setViewModelNavigate()
        setReservationListData()
    }

    private fun setContent(){
        val content = arguments?.getString("content")
        if(content != null){
            viewModel.setReservationList(content)
        }
    }

    private fun setReservationListData(){
        viewModel.locationStoreList.observe(viewLifecycleOwner) { reservationList ->
            reservationList?.let {
                with(binding){
                    if(it.isEmpty()){
                        tvEmptyText.visibility = View.VISIBLE
                        ivEmptyIcon.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        return@observe
                    }
                    else{
                        tvEmptyText.visibility = View.GONE
                        ivEmptyIcon.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE

                       recyclerView.apply{
                            adapter = reservationShopAdapter
                            layoutManager = LinearLayoutManager(context)
                        }
                        reservationShopAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun setViewModelNavigate(){
        viewModel.navigateToDetail.observe(viewLifecycleOwner) { reservationList ->
            reservationList?.let {
                openCreateBuilding(it)
                viewModel.onNavigationComplete()
            }
        }
    }

    private fun openCreateBuilding(reservationList: ReservationList) {
        val intent = Intent(context, ReservationDetailActivity::class.java)
        intent.putExtra(RESERVATION_ID, reservationList.reservationId)
        startActivity(intent)
    }

    companion object{
        const val RESERVATION_ID = "RESERVATION_ID"
    }

}
