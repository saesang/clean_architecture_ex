package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TotalInfoData
import com.example.domain.usecase.FetchTotalInfoUsecase
import com.example.domain.usecase.GetTotalInfoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val fetchTotalInfoUsecase: FetchTotalInfoUsecase,
    private val getTotalInfoUsecase: GetTotalInfoUsecase
) : ViewModel() {
    private val _totalInfoData = MutableStateFlow<BaseState<TotalInfoData>>(BaseState.None)
    val totalInfoData = _totalInfoData.asStateFlow()

    fun getTotalInfoData(username: String) {
        viewModelScope.launch {
            _totalInfoData.value = BaseState.Loading

            val data = getTotalInfoUsecase.invoke(username)
            if (data == null) {
                fetchTotalInfoData(username)
            } else {
                _totalInfoData.value = BaseState.Success(data)
            }
        }
    }

    private fun fetchTotalInfoData(username: String) {
        viewModelScope.launch {
            _totalInfoData.value = BaseState.Loading

            val data = fetchTotalInfoUsecase.invoke(username)
            _totalInfoData.value = BaseState.Success(data)
        }
    }

    fun setInitialState() {
        _totalInfoData.value = BaseState.None
    }
}