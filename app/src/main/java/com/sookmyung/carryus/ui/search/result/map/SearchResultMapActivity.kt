package com.sookmyung.carryus.ui.search.result.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchResultMapBinding
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.SimpleStoreReviewInfo
import com.sookmyung.carryus.ui.search.result.SearchResultViewModel
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class SearchResultMapActivity :
    BindingActivity<ActivitySearchResultMapBinding>(R.layout.activity_search_result_map) {
    private val viewModel by viewModels<SearchResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        showMarkerOnMap()
        startTracking()
        initStoreListView()
    }

    private fun showMarkerOnMap() {
        viewModel.storePositionList.value?.forEach { position ->
            val marker = MapPoint.mapPointWithGeoCoord(position.latitude, position.longitude)
            val markerIcon = MapPOIItem()
            markerIcon.apply {
                itemName = "marker name"
                customImageResourceId = R.drawable.ic_store_default
                customSelectedImageResourceId = R.drawable.ic_store_select
                mapPoint = marker
                setCustomImageAnchor(0.5f, 0.5f)
                isCustomImageAutoscale = false
                markerType = MapPOIItem.MarkerType.CustomImage
                tag = 0
            }
            binding.mapSearchResultMap.addPOIItem(markerIcon)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startTracking() {
        binding.mapSearchResultMap.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving // 나침반 안돌아가고 현재 위치로 안따라감

        binding.mapSearchResultMap.setCustomCurrentLocationMarkerTrackingImage(
            R.drawable.ic_store_select,
            MapPOIItem.ImageOffset(0, 0)
        )

        val locationManager: LocationManager =
            this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val currentLocation: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val currentLatitude = currentLocation?.latitude ?: 0
        val currentLongitude = currentLocation?.longitude ?: 0
        val currentPosition = Position(currentLatitude.toDouble(), currentLongitude.toDouble())
        viewModel.updateCurrentLocation(currentPosition)
    }

    private fun setViewVisibility(firstVisibility: Int, secondVisibility: Int) {
        binding.clSearchResultMapFirstStoreInfo.visibility = firstVisibility
        binding.clSearchResultMapSecondStoreInfo.visibility = secondVisibility
    }

    private fun updateStoreInfo(
        firstVisibility: Int,
        secondVisibility: Int,
        firstStoreInfo: SimpleStoreReviewInfo,
        secondStoreInfo: SimpleStoreReviewInfo? = null
    ) {
        setViewVisibility(firstVisibility, secondVisibility)

        with(binding) {
            tvSearchResultMapFirstStoreTitle.text = firstStoreInfo.storeTitle
            tvSearchResultMapFirstStoreSubTitle.text = firstStoreInfo.storeSubTitle
            ivSearchResultMapFirstStore.setImage(firstStoreInfo.storeImg)
            tvSearchResultMapFirstStoreReview.text = getString(
                R.string.search_review_score_count,
                firstStoreInfo.storeReviewScore,
                firstStoreInfo.storeReviewCount
            )
        }

        with(binding) {
            secondStoreInfo?.let {
                tvSearchResultMapSecondStoreTitle.text = secondStoreInfo.storeTitle
                tvSearchResultMapSecondStoreSubTitle.text = secondStoreInfo.storeSubTitle
                ivSearchResultMapSecondStore.setImage(secondStoreInfo.storeImg)
                tvSearchResultMapSecondStoreReview.text = getString(
                    R.string.search_review_score_count,
                    secondStoreInfo.storeReviewScore,
                    secondStoreInfo.storeReviewCount
                )
            }
        }
    }

    private fun initStoreListView() {
        viewModel.simpleStoreReviewInfoList.observe(this) { simpleStoreReviewInfoList ->
            if (simpleStoreReviewInfoList == null) {
                setViewVisibility(View.GONE, View.GONE)
            } else {
                when (simpleStoreReviewInfoList.size) {
                    1 -> {
                        updateStoreInfo(View.VISIBLE, View.GONE, simpleStoreReviewInfoList[0])
                    }

                    2 -> {
                        updateStoreInfo(
                            View.VISIBLE,
                            View.VISIBLE,
                            simpleStoreReviewInfoList[0],
                            simpleStoreReviewInfoList[1]
                        )
                    }

                    else -> {
                        setViewVisibility(View.GONE, View.GONE)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        stopTracking()
        super.onDestroy()
    }

    private fun stopTracking() {
        binding.mapSearchResultMap.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOff
    }
}
