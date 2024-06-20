package com.avrzll.recipe.data.room

import androidx.lifecycle.LiveData
import com.avrzll.recipe.data.utils.AppExecutors

class RoomRepository private constructor(private val ingredientsDao: IngredientsDao, private val appExecutors: AppExecutors) {

    fun getAllIngredients(): LiveData<List<IngredientsEntity>> = ingredientsDao.getAllIngredients()

    fun insertIngredients(ingredients: IngredientsEntity) {
        appExecutors.diskIO().execute { ingredientsDao.insertIngredients(ingredients) }
    }

    fun updateIngredients(ingredients: IngredientsEntity) {
        appExecutors.diskIO().execute { ingredientsDao.updateIngredients(ingredients) }
    }

    fun deleteIngredients(ingredients: IngredientsEntity) {
        appExecutors.diskIO().execute { ingredientsDao.deleteIngredients(ingredients) }
    }

    companion object {
        @Volatile
        private var instance: RoomRepository? = null

        fun getInstance(
            ingredientsDao: IngredientsDao,
            appExecutors: AppExecutors
        ): RoomRepository = instance ?: synchronized(this) {
            instance ?: RoomRepository(ingredientsDao, appExecutors)
        }.also { instance = it }
    }
}