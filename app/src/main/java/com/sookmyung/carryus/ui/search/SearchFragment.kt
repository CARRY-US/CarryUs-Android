package com.sookmyung.carryus.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.contains
import androidx.fragment.app.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentSearchBinding
import com.sookmyung.carryus.domain.entity.LocationStore
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.ui.search.list.SearchListActivity
import com.sookmyung.carryus.ui.search.result.SearchResultActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity.Companion.STORE_ID
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import com.sookmyung.carryus.util.binding.BindingFragment
import com.sookmyung.carryus.util.toast
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.CameraUpdate
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment :
    BindingFragment<FragmentSearchBinding>(R.layout.fragment_search),
    MapView.POIItemEventListener {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var mapView: MapView
    private lateinit var mapViewContainer: ViewGroup
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        checkLocationPermission()
        initMapView()
        initStoreListView()
        initSearchViewClickListener()
        moveToSearchList()
        moveToStoreDetail()
        clickReloadBtn()
        observeSearchStoreList()
    }

    override fun onResume() {
        super.onResume()
        if (mapViewContainer.contains(mapView)) {
            try {
                initMapView()
            } catch (e: RuntimeException) {
                Timber.e(e.toString())
            }
        }
    }

    private fun initMapView() {
        mapView = MapView(activity)
        mapView.setPOIItemEventListener(this)
        mapViewContainer = binding.mapSearch
        mapViewContainer.addView(mapView)
        showMarkerOnMap()
        startTracking()
        moveMapToUserLocation()
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    requireContext(),
                    ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    ACCESS_FINE_LOCATION
                )
            ) {
                requireContext().toast(getString(R.string.search_location_permission_request))
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        ACCESS_FINE_LOCATION,
                        ACCESS_COARSE_LOCATION
                    ),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        ACCESS_FINE_LOCATION,
                        ACCESS_COARSE_LOCATION
                    ),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                requireContext().toast(getString(R.string.search_location_permission_granted))
            } else {
                requireContext().toast(getString(R.string.search_location_permission_denied))
            }
        }
        requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
    }

    private fun initStoreListView() {
        viewModel.locationStoreList.observe(viewLifecycleOwner) { list ->
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

    private fun initSearchViewClickListener() {
        binding.tvSearchSearch.setOnClickListener {
            val toSearch = Intent(requireActivity(), SearchResultActivity::class.java)
            startActivity(toSearch)
        }
    }

    private fun showMarkerOnMap() {
        mapView.removeAllPOIItems()
        viewModel.searchStoreList.value?.forEach { list ->
            val markerIcon = MapPOIItem()
            markerIcon.apply {
                itemName = list.storeName
                isShowCalloutBalloonOnTouch = false
                customImageResourceId = R.drawable.ic_store_default
                customSelectedImageResourceId = R.drawable.ic_store_select
                mapPoint = MapPoint.mapPointWithGeoCoord(list.latitude, list.longitude)
                setCustomImageAnchor(0.5f, 0.5f)
                isCustomImageAutoscale = false
                markerType = MapPOIItem.MarkerType.CustomImage
                tag = list.storeId
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
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val currentLocation: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val currentLatitude = currentLocation?.latitude ?: 0
        val currentLongitude = currentLocation?.longitude ?: 0
        val currentPosition = Position(currentLatitude.toDouble(), currentLongitude.toDouble())
        viewModel.updateCurrentLocation(currentPosition)
    }

    private fun moveMapToUserLocation() {
        viewModel.currentLocation.observe(viewLifecycleOwner) {
            val cameraUpdate: CameraUpdate =
                CameraUpdateFactory.newMapPoint(
                    viewModel.currentLocation.value?.let {
                        MapPoint.mapPointWithGeoCoord(
                            it.latitude,
                            it.longitude
                        )
                    }
                )
            mapView.moveCamera(cameraUpdate)
        }
    }

    private fun setViewVisibility(firstVisibility: Int, secondVisibility: Int) {
        binding.clSearchFirstStoreInfo.visibility = firstVisibility
        binding.clSearchSecondStoreInfo.visibility = secondVisibility
    }

    private fun updateStoreInfo(
        firstVisibility: Int,
        secondVisibility: Int,
        firstStoreInfo: LocationStore,
        secondStoreInfo: LocationStore? = null
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
            val bundle = Bundle()
            val searchStoreArrayList = ArrayList<Parcelable>(viewModel.searchStoreList.value.orEmpty())
            bundle.putParcelableArrayList("searchStoreList", searchStoreArrayList)
            toSearchList.putExtra("searchStoreList", bundle)
            startActivity(toSearchList)
        }
    }

    private fun moveToStoreDetail() {
        binding.clSearchFirstStoreInfo.setOnClickListener {
            viewModel.locationStoreList.value?.get(0)
                ?.let { store -> viewModel.updateSelectedStoreId(store.storeId) }
            val toStoreDetail = Intent(requireActivity(), StoreDetailActivity::class.java).putExtra(
                STORE_ID,
                viewModel.selectedStoreId.value
            )
            startActivity(toStoreDetail)
        }
        binding.clSearchSecondStoreInfo.setOnClickListener {
            viewModel.locationStoreList.value?.get(1)
                ?.let { store -> viewModel.updateSelectedStoreId(store.storeId) }
            val toStoreDetail = Intent(requireActivity(), StoreDetailActivity::class.java)
            startActivity(toStoreDetail)
        }
    }

    // TODO 지도 움직일 때마다, 재검색 버튼이 뜨게 하기
    private fun clickReloadBtn() {
        binding.btnSearchReload.setOnClickListener {
            viewModel.updateCurrentPosition(
                mapView.mapPointBounds.bottomLeft.mapPointGeoCoord.latitude,
                mapView.mapPointBounds.topRight.mapPointGeoCoord.latitude,
                mapView.mapPointBounds.bottomLeft.mapPointGeoCoord.longitude,
                mapView.mapPointBounds.topRight.mapPointGeoCoord.longitude
            )
        }
    }

    private fun observeSearchStoreList() {
        viewModel.searchStoreList.observe(viewLifecycleOwner) {
            showMarkerOnMap()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
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
