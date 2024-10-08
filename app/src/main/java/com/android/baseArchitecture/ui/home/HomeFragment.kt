package com.android.baseArchitecture.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.android.baseArchitecture.databinding.FragmentHomeBinding
import com.android.core.view.BaseFragment
import com.android.baseArchitecture.ui.home.adapter.UserAdapter
import com.android.baseArchitecture.utility.checkGps
import com.android.baseArchitecture.viewmodel.SharedViewModel
import com.android.core.utility.isFalse
import com.android.core.utility.isTrue
import com.android.core.utility.myScope
import com.android.core.utility.vertical
import com.android.domain.model.user.UserUiModel
import com.android.domain.repository.LocationService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {

    /**Argument ile veri almayı saglıyor*/
    private val args: HomeFragmentArgs by navArgs()

    private val adapter by lazy { UserAdapter(::userAdapterHandle) }

    @Inject
    lateinit var locationService: LocationService


    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        super.initUI()
        binding.tvTitle.text = args.title
        createAdapter()

        checkGps()
    }

    private fun checkGps() {
        //Telefonda gps acık olup olmadıgını kontrol eder
        activity.checkGps(getLocation = {
            getLocation()
        }, notFoundLocationService = {
//
        })
    }

    private fun getLocation() {
        askLocationPermission { isGranted ->
            isGranted.isTrue {
                locationService.getLocation().filterNotNull().onEach { location ->
                    showToast("${location.latitude} - ${location.longitude}")
                }.launchIn(myScope)
            }.isFalse {
                showToast("İzin vermelisiniz")
            }
        }

    }

    private fun createAdapter() {
        binding.rvList.vertical(adapter)
    }

    /**
     * Bu fonksiyon bir kere calısır bu yüzden network isteklerini bu fonksiyon içerisinden yapalım
     * */
    override fun runOnce() {
        super.runOnce()
        viewModel.getUsers()

    }


    override fun setReceivers() {
        super.setReceivers()

        with(viewModel) {
            users.onEach(::handleUsers).launchIn(myScope)
        }
    }

    override fun setListeners() {
        super.setListeners()

        with(binding) {
            btnSendData.setOnClickListener {
                // sharedViewModel.data.value = "Bir önceki sayfadan gönderilen data"

                viewModel.navigateBack()
                sharedViewModel.data.value = "Bir önceki sayfadan gönderilen data"
            }
        }
    }

    private fun handleUsers(users: List<UserUiModel>) {
        adapter.submitList(users)
    }

    private fun userAdapterHandle(event: UserAdapter.Event) {
        if (event is UserAdapter.Event.onClickItem) {
            showToast(event.item.name)
        }
    }


}