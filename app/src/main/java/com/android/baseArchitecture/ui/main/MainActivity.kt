package com.android.baseArchitecture.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.android.core.view.BaseActivity
import com.android.data.local.PrefHelper
import com.android.baseArchitecture.databinding.ActivityMainBinding
import com.android.baseArchitecture.utility.AppConstants
import com.android.baseArchitecture.utility.GPSUtility
import com.android.baseArchitecture.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {


    @Inject
    lateinit var prefHelper: PrefHelper
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private var _gpsListener: (isOk: Boolean) -> Unit = { _ -> }

    val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    fun checkGPS(
        successCallback: () -> Unit,
        errorCallback: () -> Unit,
        notFoundLocationService: (() -> Unit?) = {}
    ) {
        GPSUtility.enableLocationSettings(
            this,
            successCallback,
            errorCallback,
            notFoundLocationService
        )
    }

    fun gpsListener(f: (isOk: Boolean) -> Unit) {
        _gpsListener = f
    }

    @Deprecated("Deprecated in Java")
    @Suppress("deprecation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppConstants.LOCATION_SETTING_REQUEST && resultCode == Activity.RESULT_OK) {
            _gpsListener.invoke(true)
        } else {
            _gpsListener.invoke(false)
        }
    }

}