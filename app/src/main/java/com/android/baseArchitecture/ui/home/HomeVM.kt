package com.android.baseArchitecture.ui.home

import com.android.core.viewmodel.BaseViewModel
import com.android.domain.model.user.UserUiModel
import com.android.domain.usecases.users.UserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeVM @Inject constructor(
    private val userListUseCase: UserListUseCase
) : BaseViewModel() {

    /**MutableLiveData yerine artık bunu kullanıyoruz
     * Datayı sürekli canlı tutar fragment her açıldığında tetiklenir
     * */
    private val _users = MutableStateFlow<List<UserUiModel>>(emptyList())
    val users = _users.asStateFlow()

    /**SingleLive event yerine artık bunu kullanıyoruz
     * Yanlızca fragment ilk açılışta bir kere tetiklenir
     * */
    /*private val _showMessage = MutableSharedFlow<String>()
    val showMessage = _showMessage.asSharedFlow()*/

    fun getUsers() {
        userListUseCase.execute(UserListUseCase.Params(1)).dataHandling(
            success = { users ->
                _users.value = users
            }
        )
    }

}