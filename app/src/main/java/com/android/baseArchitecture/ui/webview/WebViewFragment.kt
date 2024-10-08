package com.android.baseArchitecture.ui.webview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.core.view.BaseFragment
import com.android.data.local.PrefHelper
import com.android.baseArchitecture.databinding.FragmentWebViewBinding
import com.android.baseArchitecture.ui.onboarding.OnBoardingVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding, OnBoardingVM>() {

    @Inject
    lateinit var prefHelper: PrefHelper


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentWebViewBinding {
        return FragmentWebViewBinding.inflate(inflater, container, false)
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun initUI() {
        super.initUI()

        with(binding) {

            webView.apply {
                settings.javaScriptEnabled = true
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                settings.setSupportZoom(true)
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        view?.loadUrl(request?.url.toString())
                        return super.shouldOverrideUrlLoading(view, request)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                    }
                }
                loadUrl("args.webUrl")

            }


            /*
        if (args.title.isNullOrEmpty().not()) {
            binding.header.changeTitle(args.title.orEmpty())
        }*/
        }
    }

}