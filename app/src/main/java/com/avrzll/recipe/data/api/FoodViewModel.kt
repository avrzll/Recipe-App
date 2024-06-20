package com.avrzll.recipe.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FoodViewModel (private val apiRepository: APIRepository) : ViewModel() {
    // Mendeklarasikan variabel listPlayer yang berisi LiveData dari List APIResponse dari repository
    val listFood: LiveData<List<FoodList>> = apiRepository.listFood
    val listFoodMatchIngreds: LiveData<List<FoodList>> = apiRepository.listFoodMatchIngreds
    val listFoodMatchAnyIngreds: LiveData<List<FoodList>> = apiRepository.listFoodMatchAnyIngreds
    val listFoodByName: LiveData<List<FoodList>> = apiRepository.listFoodByName

    // Mendeklarasikan variabel isLoading yang berisi LiveData dari Boolean (status loading) dari repository
    val isLoading: LiveData<Boolean> = apiRepository.isLoading

    // Fungsi untuk mendapatkan semua data dari repository
    fun getAllFoods() {
        apiRepository.getAllFoods()
    }

    fun getFoodMatchIngreds(ingredients: String) {
        apiRepository.getMatchIngreds(ingredients)
    }

    fun getMatchAnyIngreds(ingredients: String) {
        apiRepository.getMatchAnyIngreds(ingredients)
    }

    fun getMatchName(name: String) {
        apiRepository.getMatchName(name)
    }
}