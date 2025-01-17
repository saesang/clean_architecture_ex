package com.example.domain.usecase

import com.example.domain.repository.TotalInfoRepository
import javax.inject.Inject

class FetchTotalInfoUsecase @Inject constructor(
    private val totalInfoRepository: TotalInfoRepository
){
    // 서버 요청
    suspend operator fun invoke(username: String) = totalInfoRepository.fetchTotalInfo(username)
}