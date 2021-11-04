package com.mota.pebblebee.ui.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mota.pebblebee.common.Resource
import com.mota.pebblebee.domain.use_case.GetUserUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AccountScreenViewModel @Inject constructor(
    private val getCurrentUser: GetUserUseCase
) : ViewModel() {

    init {
        getUser()
    }

    private fun getUser() {
        getCurrentUser().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    //update UI or something like that
                    //
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}