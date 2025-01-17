package com.example.domain.repository

interface TotalInfoRepository {
    // username이 같고 date가 오늘인 객체가 DB에 존재하면, DB 요청
    suspend fun getTotalInfo(username: String)

    // 서버 요청
    suspend fun fetchTotalInfo(username: String)
}