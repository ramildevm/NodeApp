package com.example.nodeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nodeapp.data.database.dao.NodeDao
import com.example.nodeapp.data.database.di.ApplicationScope
import com.example.nodeapp.data.database.entities.NodeEntity
import com.example.nodeapp.data.helpers.NodeHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    version = 1,
    entities = [NodeEntity::class],
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract val nodeDao: NodeDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val name = NodeHelper.generateNodeName(null, 1)
            val node = NodeEntity(
                id = 1,
                name = name,
            )
            applicationScope.launch {
                database.get().nodeDao.insertNode(
                    node,
                )
            }
        }
    }
}
