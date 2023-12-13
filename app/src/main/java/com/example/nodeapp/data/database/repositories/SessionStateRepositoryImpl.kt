package com.example.nodeapp.data.database.repositories

import android.content.Context
import com.example.nodeapp.data.helpers.SharedPreferencesUtil
import com.example.nodeapp.domain.repository.SessionStateRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionStateRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SessionStateRepository {

    override fun getLastSavedNodeId(): Int {
        return SharedPreferencesUtil.getSharedIntData(context, SESSION_KEY, 1)
    }

    override suspend fun saveCurrentNodeId(id: Int?) {
        SharedPreferencesUtil.setSharedData(context, SESSION_KEY, id ?: 1)
    }

    companion object {
        const val SESSION_KEY = "SESSION_KEY"
    }

}
