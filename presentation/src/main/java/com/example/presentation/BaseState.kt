package com.example.presentation

sealed class BaseState<out T>(val _data : T?)  {
    companion object {
        val initial = BaseState.None
    }
    data object None: BaseState<Nothing>(_data = null)
    data object Loading : BaseState<Nothing>(_data = null)
    data class Error(val errorCode: Int) : BaseState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : BaseState<T>(_data = data)
}