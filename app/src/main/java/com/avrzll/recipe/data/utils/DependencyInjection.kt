package com.avrzll.recipe.data.utils

import android.content.Context
import com.avrzll.recipe.data.room.AppDatabase
import com.avrzll.recipe.data.room.RoomRepository

object DependencyInjection {
    fun provideRepository(context: Context): RoomRepository {
        val database = AppDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val dao = database.ingredientsDao()
        return RoomRepository.getInstance(dao, appExecutors)
    }
}