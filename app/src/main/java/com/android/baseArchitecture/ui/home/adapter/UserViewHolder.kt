package com.android.baseArchitecture.ui.home.adapter

import android.view.ViewGroup
import com.android.baseArchitecture.databinding.ItemUserBinding
import com.android.core.view.BaseViewHolder
import com.android.core.view.inflate
import com.android.domain.model.user.UserUiModel


class UserViewHolder(
    val binding: ItemUserBinding,
    private val onEvent: (UserAdapter.Event) -> Unit
) :
    BaseViewHolder<UserUiModel>(binding.root) {

    override fun bind(item: UserUiModel) {
        with(binding) {
            tvTitle.text = item.name
            tvUrl.text = item.email

            binding.root.setOnClickListener {
                onEvent(UserAdapter.Event.onClickItem(item))
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, onEvent: (UserAdapter.Event) -> Unit): UserViewHolder {
            val binding =
                ItemUserBinding.inflate(parent.inflate, parent, false)
            return UserViewHolder(binding, onEvent)
        }
    }
}