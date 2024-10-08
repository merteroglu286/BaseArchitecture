package com.android.baseArchitecture.ui.onboarding

import com.android.baseArchitecture.ui.onboarding.splash.SplashFragmentDirections
import com.android.core.viewmodel.BaseViewModel
import com.android.data.local.PrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class OnBoardingVM @Inject constructor(
    private val prefHelper: PrefHelper
) : BaseViewModel() {


    fun goHomeScreen() {
        navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment("HomeTitle"))
    }

}