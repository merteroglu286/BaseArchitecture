package com.android.domain.usecases.users

import com.android.domain.BaseFlowUseCase
import com.android.domain.model.DataState
import com.android.domain.model.user.UserUiModel
import com.android.domain.repository.UsersRepository
import com.android.domain.util.ErrorPopUpType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class UserListUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseFlowUseCase<UserListUseCase.Params, List<UserUiModel>>() {

    /**
     * Servis isteklerinde loading göstermek istemiyorsan burayı false geçebiliriz default olarak true ayarlı
     * */
    /*  override fun showLoading(): Boolean {
          return false
      }*/

    /**
     * Servis istekleri sonrası hata durumda ekranda popup göstermek ıstemıyorsak burayı NONE geçebiliriz default olarak ERROR ayarlı
     * error popup tipleri çoğaltilabilir
     * */
    /*   override fun errorPopupType(): ErrorPopUpType {
           return ErrorPopUpType.NONE
       }*/

    override fun run(request: Params): Flow<DataState<List<UserUiModel>>> = flow {
        emit(usersRepository.getUsers(request.id))
    }


    data class Params(
        var id: Int
    )

}
