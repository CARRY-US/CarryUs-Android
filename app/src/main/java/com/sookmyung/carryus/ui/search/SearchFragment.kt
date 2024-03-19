package com.sookmyung.carryus.ui.search

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentSearchBinding
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.ui.search.list.SearchListActivity
import com.sookmyung.carryus.ui.search.result.SearchResultActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import com.sookmyung.carryus.util.binding.BindingFragment

class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel by viewModels<SearchViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

//        showMarkerOnMap()
//        startTracking()
//        moveMapToUserLocation()
        initStoreListView()
        initSearchViewClickListener()
        moveToSearchList()
        moveToStoreDetail()
    }

    //TODO marker 클릭 시, 보이게 하기
    private fun initStoreListView() {
        viewModel.searchStoreList.observe(viewLifecycleOwner) { searchStoreList ->
            if (searchStoreList == null) {
                setViewVisibility(View.GONE, View.GONE)
            } else {
                when (searchStoreList.size) {
                    1 -> {
                        updateStoreInfo(View.VISIBLE, View.GONE, searchStoreList[0])
                    }

                    2 -> {
                        updateStoreInfo(
                            View.VISIBLE,
                            View.VISIBLE,
                            searchStoreList[0],
                            searchStoreList[1]
                        )
                    }

                    else -> {
                        setViewVisibility(View.GONE, View.GONE)
                    }
                }
            }
        }
    }

    private fun initSearchViewClickListener() {
        binding.tvSearchSearch.setOnClickListener {
            val toSearch = Intent(requireActivity(), SearchResultActivity::class.java)
            startActivity(toSearch)
        }
    }

//    private fun showMarkerOnMap() {
//        viewModel.storeList.value?.forEach { position ->
//            val marker = MapPoint.mapPointWithGeoCoord(position.latitude, position.longitude)
//            val markerIcon = MapPOIItem()
//            markerIcon.apply {
//                itemName = "marker name"
//                customImageResourceId = R.drawable.ic_store_default
//                customSelectedImageResourceId = R.drawable.ic_store_select
//                mapPoint = marker
//                setCustomImageAnchor(0.5f, 0.5f)
//                isCustomImageAutoscale = false
//                markerType = MapPOIItem.MarkerType.CustomImage
//                tag = 0
//            }
//            binding.mapSearch.addPOIItem(markerIcon)
//        }
//    }

    private fun checkLocationService(): Boolean {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    // 현재 사용자 위치추적 및 경도, 위도 받아오기
//    @SuppressLint("MissingPermission")
//    private fun startTracking() {
//        binding.mapSearch.currentLocationTrackingMode =
//            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving // 나침반 안돌아가고 현재 위치로 안따라감
//
//        binding.mapSearch.setCustomCurrentLocationMarkerTrackingImage(
//            R.drawable.ic_store_select,
//            MapPOIItem.ImageOffset(0, 0)
//        )
//
//        val locationManager: LocationManager =
//            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val currentLocation: Location? =
//            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//        val currentLatitude = currentLocation?.latitude ?: 0
//        val currentLongitude = currentLocation?.longitude ?: 0
//        val currentPosition = Position(currentLatitude.toDouble(), currentLongitude.toDouble())
//        viewModel.updateCurrentLocation(currentPosition)
//    }

//    private fun moveMapToUserLocation() {
//        viewModel.currentLocation.observe(viewLifecycleOwner) {
//            val cameraUpdate: CameraUpdate =
//                CameraUpdateFactory.newMapPoint(
//                    viewModel.currentLocation.value?.let {
//                        MapPoint.mapPointWithGeoCoord(
//                            it.latitude,
//                            it.longitude
//                        )
//                    }
//                )
//            binding.mapSearch.moveCamera(cameraUpdate)
//        }
//    }

    private fun setViewVisibility(firstVisibility: Int, secondVisibility: Int) {
        binding.clSearchFirstStoreInfo.visibility = firstVisibility
        binding.clSearchSecondStoreInfo.visibility = secondVisibility
    }

    private fun updateStoreInfo(
        firstVisibility: Int,
        secondVisibility: Int,
        firstStoreInfo: StoreSearchResult,
        secondStoreInfo: StoreSearchResult? = null
    ) {
        setViewVisibility(firstVisibility, secondVisibility)

        with(binding) {
            tvSearchFirstStoreTitle.text = firstStoreInfo.storeName
            tvSearchFirstStoreSubTitle.text = firstStoreInfo.storeLocation
            ivSearchFirstStore.setImage(firstStoreInfo.storeImgUrl)
            tvSearchFirstStoreReview.text = getString(
                R.string.search_review_score_count,
                firstStoreInfo.storeRatingAverage,
                firstStoreInfo.storeReviewCount
            )
        }

        with(binding) {
            secondStoreInfo?.let {
                tvSearchSecondStoreTitle.text = secondStoreInfo.storeName
                tvSearchSecondStoreSubTitle.text = secondStoreInfo.storeLocation
                ivSearchSecondStore.setImage(secondStoreInfo.storeImgUrl)
                tvSearchSecondStoreReview.text = getString(
                    R.string.search_review_score_count,
                    secondStoreInfo.storeRatingAverage,
                    secondStoreInfo.storeReviewCount
                )
            }
        }
    }

    private fun moveToSearchList() {
        binding.fabSearch.setOnClickListener {
            val toSearchList = Intent(requireActivity(), SearchListActivity::class.java)
            startActivity(toSearchList)
        }
    }

    private fun moveToStoreDetail(){
        binding.clSearchFirstStoreInfo.setOnClickListener {
            val toStoreDetail = Intent(requireActivity(), StoreDetailActivity::class.java).putExtra("storeId", viewModel.selectedStoreId.value)
            startActivity(toStoreDetail)
        }
        binding.clSearchSecondStoreInfo.setOnClickListener {
            val toStoreDetail = Intent(requireActivity(), StoreDetailActivity::class.java)
            startActivity(toStoreDetail)
        }
    }

    override fun onDestroyView() {
//        stopTracking()
        super.onDestroyView()
    }

//    private fun stopTracking() {
//        binding.mapSearch.currentLocationTrackingMode =
//            MapView.CurrentLocationTrackingMode.TrackingModeOff
//    }
}
