package com.example.domain.repository

import com.example.domain.model.TotalInfoData

interface TotalInfoRepository {
    // username이 같고 date가 오늘인 객체가 DB에 존재하면, DB 요청
    suspend fun getTotalInfo(username: String): TotalInfoData?

    // 서버 요청
    suspend fun fetchTotalInfo(username: String): TotalInfoData
}