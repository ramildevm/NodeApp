package com.example.nodeapp.data.database.di

import com.example.nodeapp.data.database.repositories.NodeRepositoryImpl
import com.example.nodeapp.data.database.repositories.SessionStateRepositoryImpl
import com.example.nodeapp.domain.repository.NodeRepository
import com.example.nodeapp.domain.repository.SessionStateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindSessionStateRepository(
        repository: SessionStateRepositoryImpl
    ): SessionStateRepository
    @Binds
    @Singleton
    fun bindNodeRepository(
        repository: NodeRepositoryImpl
    ): NodeRepository
}