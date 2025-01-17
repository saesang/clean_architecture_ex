package com.example.domain.usecase

import com.example.domain.repository.TotalInfoRepository
import javax.inject.Inject

class GetTotalInfoUsecase @Inject constructor(
    private val totalInfoRepository: TotalInfoRepository
){
    // username이 같고 date가 오늘인 객체가 DB에 존재하면, DB에 TotalInfoData 요청
    suspend operator fun invoke(username: String) = totalInfoRepository.getTotalInfo(username)
}