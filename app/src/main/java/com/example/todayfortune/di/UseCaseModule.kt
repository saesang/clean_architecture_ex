package com.example.todayfortune.di

import com.example.domain.repository.TotalInfoRepository
import com.example.domain.usecase.FetchTotalInfoUsecase
import com.example.domain.usecase.GetTotalInfoUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun fetchTotalInfoUsecase(
        totalInfoRepository: TotalInfoRepository
    ): FetchTotalInfoUsecase {
        return FetchTotalInfoUsecase(totalInfoRepository)
    }

    @Provides
    fun getTotalInfoUsecase(
        totalInfoRepository: TotalInfoRepository
    ): GetTotalInfoUsecase {
        return GetTotalInfoUsecase(totalInfoRepository)
    }
}