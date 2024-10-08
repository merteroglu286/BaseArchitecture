package com.android.baseArchitecture.ui.onboarding.splash


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.android.core.view.BaseFragment
import com.android.baseArchitecture.databinding.FragmentSplashBinding
import com.android.baseArchitecture.ui.onboarding.OnBoardingVM
import com.android.baseArchitecture.utility.AppConstants.SPLASH_DELAY
import com.android.baseArchitecture.viewmodel.SharedViewModel
import com.android.core.utility.myScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, OnBoardingVM>() {

    val sharedViewModel: SharedViewModel by activityViewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        super.initUI()
    }

    override fun runOnce() {
        super.runOnce()

        with(binding) {
            /*    lottieView.apply {
                    playAnimation()
                    addAnimatorListener(object : Animator.AnimatorListener {

                        override fun onAnimationStart(animation: Animator) {

                        }

                        override fun onAnimationEnd(animation: Animator) {

                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {

                        }

                    })
                }*/

            lifecycleScope.launchWhenCreated {
                delay(SPLASH_DELAY)
                viewModel.goHomeScreen()
            }
        }

    }

    override fun setReceivers() {
        super.setReceivers()

        sharedViewModel.data.filterNotNull().onEach { data ->
            showToast(data)
            sharedViewModel.data.value = null
        }.launchIn(myScope)
    }

}