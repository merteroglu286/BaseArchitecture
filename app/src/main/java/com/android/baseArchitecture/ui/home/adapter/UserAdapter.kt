package com.android.baseArchitecture.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.user.UserUiModel

class UserAdapter(
    private val onEvent: (Event) -> Unit
) : ListAdapter<UserUiModel, UserViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent, onEvent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    /**NotifyDatasetchanged yerine artık diffutil kullanıyoruz*/
    private class DiffCallback : DiffUtil.ItemCallback<UserUiModel>() {

        override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UserUiModel,
            newItem: UserUiModel
        ) = oldItem == newItem

    }

    /**interface ile geri callback göndermek yerine artık sealed class yada sealed interfaceleri kullanıyoruz*/
    sealed interface Event {
        data class onClickItem(val item: UserUiModel) : Event
    }
}