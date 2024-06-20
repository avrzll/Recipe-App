package com.avrzll.recipe.data.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avrzll.recipe.data.utils.DependencyInjection


class IngredientsViewModelFactory private constructor(private val ingredientsRepository: RoomRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientsViewModel::class.java)) {
            return IngredientsViewModel(ingredientsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: IngredientsViewModelFactory? = null
        fun getInstance(context: Context): IngredientsViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: IngredientsViewModelFactory(DependencyInjection.provideRepository(context))
            }.also { instance = it }
    }
}