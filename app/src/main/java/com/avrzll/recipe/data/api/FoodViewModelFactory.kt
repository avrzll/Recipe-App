package com.avrzll.recipe.data.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FoodViewModelFactory private constructor(private val apiRepository: APIRepository) :
    ViewModelProvider.NewInstanceFactory() {

    // Fungsi ini digunakan untuk membuat instance dari ViewModel
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Jika modelClass adalah atau merupakan subclass dari ExampleViewModel
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            // Membuat dan mengembalikan instance dari ExampleViewModel
            return FoodViewModel(apiRepository) as T
        }
        // Jika modelClass bukan ExampleViewModel atau subclass dari ExampleViewModel, lemparkan pengecualian
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    // Objek companion yang digunakan untuk membuat instance dari ExampleViewModelFactory
    companion object {
        // Variabel instance digunakan untuk menyimpan instance dari ExampleViewModelFactory
        @Volatile
        private var instance: FoodViewModelFactory? = null

        // Fungsi ini digunakan untuk mendapatkan instance dari ExampleViewModelFactory
        fun getInstance(): FoodViewModelFactory =
        // Jika instance tidak null, kembalikan instance
            // Jika instance null, buat instance baru
            instance ?: synchronized(this) {

                instance
                    ?: FoodViewModelFactory(APIRepository())
            }.also { instance = it }
    }
}
