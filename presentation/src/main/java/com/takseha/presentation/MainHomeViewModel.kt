package com.takseha.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TotalInfoData
import com.example.domain.usecase.FetchTotalInfoUsecase
import com.example.domain.usecase.GetTotalInfoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val fetchTotalInfoUsecase: FetchTotalInfoUsecase,
    private val getTotalInfoUsecase: GetTotalInfoUsecase
) : ViewModel() {
    private val _totalInfoData = MutableSharedFlow<BaseState<TotalInfoData>>(replay = 0)
    val totalInfoData = _totalInfoData.asSharedFlow()

    fun getTotalInfoData(username: String) {
        viewModelScope.launch {
            _totalInfoData.emit(BaseState.Loading)

            val data = getTotalInfoUsecase.invoke(username)
            if (data == null) {
                fetchTotalInfoData(username)
            } else {
                _totalInfoData.emit(BaseState.Success(data))
            }
        }
    }

    private fun fetchTotalInfoData(username: String) {
        viewModelScope.launch {
            _totalInfoData.emit(BaseState.Loading)

            val data = fetchTotalInfoUsecase.invoke(username)
            _totalInfoData.emit(BaseState.Success(data))
        }
    }
}