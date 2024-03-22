package com.sookmyung.carryus.ui.search.result.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchResultMapBinding
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.ui.search.result.SearchResultViewModel
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
@AndroidEntryPoint
class SearchResultMapActivity :
    BindingActivity<ActivitySearchResultMapBinding>(R.layout.activity_search_result_map) {
    private val viewModel by viewModels<SearchResultViewModel>()
    private lateinit var mapView: MapView
    private lateinit var mapViewContainer: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initMapView()
        initStoreListView()
    }
    private fun initMapView() {
        mapView = MapView(this)
        showMarkerOnMap()
        startTracking()
        mapViewContainer = binding.mapSearchResultMap
        mapViewContainer.addView(mapView)
    }
    private fun showMarkerOnMap() {
        viewModel.searchResultList.value?.forEach { list ->
            val marker = MapPoint.mapPointWithGeoCoord(list.latitude, list.longitude)
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
            mapView.addPOIItem(markerIcon)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startTracking() {
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving // 나침반 안돌아가고 현재 위치로 안따라감

        mapView.setCustomCurrentLocationMarkerTrackingImage(
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
        firstStoreInfo: StoreSearchResult,
        secondStoreInfo: StoreSearchResult? = null
    ) {
        setViewVisibility(firstVisibility, secondVisibility)

        with(binding) {
            tvSearchResultMapFirstStoreTitle.text = firstStoreInfo.storeName
            tvSearchResultMapFirstStoreSubTitle.text = firstStoreInfo.storeLocation
            ivSearchResultMapFirstStore.setImage(firstStoreInfo.storeImgUrl)
            tvSearchResultMapFirstStoreReview.text = getString(
                R.string.search_review_score_count,
                firstStoreInfo.storeRatingAverage,
                firstStoreInfo.storeReviewCount
            )
        }

        with(binding) {
            secondStoreInfo?.let {
                tvSearchResultMapSecondStoreTitle.text = secondStoreInfo.storeName
                tvSearchResultMapSecondStoreSubTitle.text = secondStoreInfo.storeLocation
                ivSearchResultMapSecondStore.setImage(secondStoreInfo.storeImgUrl)
                tvSearchResultMapSecondStoreReview.text = getString(
                    R.string.search_review_score_count,
                    secondStoreInfo.storeRatingAverage,
                    secondStoreInfo.storeReviewCount
                )
            }
        }
    }

    private fun initStoreListView() {
        viewModel.searchResultList.observe(this) { searchResultList ->
            if (searchResultList == null) {
                setViewVisibility(View.GONE, View.GONE)
            } else {
                when (searchResultList.size) {
                    1 -> {
                        updateStoreInfo(View.VISIBLE, View.GONE, searchResultList[0])
                    }

                    2 -> {
                        updateStoreInfo(
                            View.VISIBLE,
                            View.VISIBLE,
                            searchResultList[0],
                            searchResultList[1]
                        )
                    }

                    else -> {
                        setViewVisibility(View.GONE, View.GONE)
                    }
                }
            }
        }
    }

    override fun onPause() {
        stopTracking()
        finishMap()
        super.onPause()
    }

    override fun onDestroy() {
        stopTracking()
        finishMap()
        super.onDestroy()
    }

    private fun stopTracking() {
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOff
    }

    private fun finishMap() {
        mapViewContainer.removeView(mapView)
    }
}
