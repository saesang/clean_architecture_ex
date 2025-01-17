package com.example.data.repositoryImpl

import com.example.data.dto.DummyData
import com.example.data.mapper.TotalInfoMapper
import com.example.data.retrofit.ServerApi
import com.example.data.room.TodayFortuneDb
import com.example.domain.model.TotalInfoData
import com.example.domain.repository.TotalInfoRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class TotalInfoRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi,
    private val todayFortuneDb: TodayFortuneDb
) : TotalInfoRepository {
    override suspend fun getTotalInfo(username: String): TotalInfoData? {
        // db 관련 에러 처리 생략
        // 한 번만 값을 가져으기 때문에 collect() 대신 first() 사용
        val totalInfoEntity = todayFortuneDb.dao().getTotalInfoByUsername(username).firstOrNull()

        return TotalInfoMapper.mapperToTotalInfoData(totalInfoEntity)
    }

    override suspend fun fetchTotalInfo(username: String): TotalInfoData {
        // 서버 통신 관련 에러 처리 생략
//        val fortuneInfo = serverApi.fetchFortuneInfo(username).body()!!
//        val userInfo = serverApi.fetchUserInfo(username).body()!!

        // 더미 데이터로 테스트
        val fortuneInfo = DummyData.fortuneInfoList.find { it.username == username }!!
        val userInfo = DummyData.userInfoList.find { it.username == username }!!

        val totalInfoEntity = TotalInfoMapper.mapperToTotalInfoEntity(fortuneInfo, userInfo)

        // 서버 반환 데이터 db에 저장
        todayFortuneDb.dao().insertTotalInfo(totalInfoEntity)

        return TotalInfoMapper.mapperToTotalInfoData(totalInfoEntity)!!
    }
}