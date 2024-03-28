package com.sookmyung.carryus.ui.search.result.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.contains
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchResultMapBinding
import com.sookmyung.carryus.domain.entity.LocationStore
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.CameraUpdate
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber

@AndroidEntryPoint
class SearchResultMapActivity :
    BindingActivity<ActivitySearchResultMapBinding>(R.layout.activity_search_result_map),
    MapView.POIItemEventListener {
    private val viewModel by viewModels<SearchResultMapViewModel>()
    private lateinit var mapView: MapView
    private lateinit var mapViewContainer: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getBundleData()
        initMapView()
        initStoreListView()
        moveToStoreDetail()
    }

    override fun onResume() {
        super.onResume()
        if (!mapViewContainer.contains(mapView)) {
            try {
                initMapView()
            } catch (e: RuntimeException) {
                Timber.e(e.toString())
            }
        }
    }

    private fun getBundleData() {
        val bundle = intent.getBundleExtra("searchStoreList")
        val searchStoreList = bundle?.getParcelableArrayList<StoreSearchResult>("searchStoreList")

        if (searchStoreList != null) {
            viewModel.updateSearchStoreList(searchStoreList)
        } else {
            viewModel.updateSearchStoreList(emptyList())
        }
    }

    private fun initMapView() {
        mapView = MapView(this)
        mapView.setPOIItemEventListener(this)
        mapViewContainer = binding.mapSearchResultMap
        mapViewContainer.addView(mapView)

        moveMapMiddleLocation()
        showMarkerOnMap()
    }

    private fun showMarkerOnMap() {
        viewModel.searchResultList.value?.forEach { list ->
            val marker = MapPoint.mapPointWithGeoCoord(list.latitude, list.longitude)
            val markerIcon = MapPOIItem()
            markerIcon.apply {
                itemName = list.storeName
                customImageResourceId = R.drawable.ic_store_default
                isShowCalloutBalloonOnTouch = false
                customSelectedImageResourceId = R.drawable.ic_store_select
                mapPoint = marker
                setCustomImageAnchor(0.5f, 0.5f)
                isCustomImageAutoscale = false
                markerType = MapPOIItem.MarkerType.CustomImage
                tag = list.storeId
            }
            mapView.addPOIItem(markerIcon)
        }
    }

    private fun setViewVisibility(firstVisibility: Int, secondVisibility: Int) {
        binding.clSearchResultMapFirstStoreInfo.visibility = firstVisibility
        binding.clSearchResultMapSecondStoreInfo.visibility = secondVisibility
    }

    private fun updateStoreInfo(
        firstVisibility: Int,
        secondVisibility: Int,
        firstStoreInfo: LocationStore,
        secondStoreInfo: LocationStore? = null
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
        viewModel.locationStoreList.observe(this) { list ->
            if (list == null) {
                setViewVisibility(View.GONE, View.GONE)
            } else {
                when (list.size) {
                    1 -> {
                        updateStoreInfo(View.VISIBLE, View.GONE, list[0])
                    }

                    2 -> {
                        updateStoreInfo(
                            View.VISIBLE,
                            View.VISIBLE,
                            list[0],
                            list[1]
                        )
                    }

                    else -> {
                        setViewVisibility(View.GONE, View.GONE)
                    }
                }
            }
        }
    }

    private fun moveMapMiddleLocation() {
        viewModel.searchResultList.observe(this) {
            val cameraUpdate: CameraUpdate =
                CameraUpdateFactory.newMapPoint(
                    viewModel.searchResultList.value?.let { list ->
                        MapPoint.mapPointWithGeoCoord(
                            list[0].latitude,
                            list[0].longitude
                        )
                    }
                )
            mapView.moveCamera(cameraUpdate)
        }
    }

    private fun moveToStoreDetail() {
        binding.clSearchResultMapFirstStoreInfo.setOnClickListener {
            viewModel.locationStoreList.value?.get(0)
                ?.let { store -> viewModel.updateSelectedStoreId(store.storeId) }
            val toStoreDetail = Intent(this, StoreDetailActivity::class.java).putExtra(
                StoreDetailActivity.STORE_ID,
                viewModel.selectedStoreId.value
            )
            stopTracking()
            finishMap()
            startActivity(toStoreDetail)
        }
        binding.clSearchResultMapSecondStoreInfo.setOnClickListener {
            viewModel.locationStoreList.value?.get(1)
                ?.let { store -> viewModel.updateSelectedStoreId(store.storeId) }
            stopTracking()
            finishMap()
            val toStoreDetail = Intent(this, StoreDetailActivity::class.java)
            startActivity(toStoreDetail)
        }
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

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        val storeId = p1?.tag ?: 0
        viewModel.findCoordinatesByStoreId(storeId)
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }
}
