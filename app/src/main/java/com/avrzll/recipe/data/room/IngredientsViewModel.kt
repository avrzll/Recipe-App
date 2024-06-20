package com.avrzll.recipe.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class IngredientsViewModel(private val ingredientsRepository: RoomRepository) : ViewModel() {

    fun insertIngredients(ingredients: IngredientsEntity) {
        ingredientsRepository.insertIngredients(ingredients)
    }

    fun getAllIngredients(): LiveData<List<IngredientsEntity>> {
        return ingredientsRepository.getAllIngredients()
    }

    fun updateIngredients(ingredients: IngredientsEntity) {
        ingredientsRepository.updateIngredients(ingredients)
    }

    fun deleteIngredients(ingredients: IngredientsEntity) {
        ingredientsRepository.deleteIngredients(ingredients)
    }
}