package com.avrzll.recipe.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avrzll.recipe.data.api.APIConfig
import com.avrzll.recipe.data.api.APIResponse
import com.avrzll.recipe.data.api.FoodList
import com.avrzll.recipe.data.room.IngredientsDao
import com.avrzll.recipe.data.room.IngredientsEntity
import com.avrzll.recipe.data.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepository {
    private val _listFood = MutableLiveData<List<FoodList>>()
    val listFood: LiveData<List<FoodList>> = _listFood

    private val _listFoodMatchIngreds = MutableLiveData<List<FoodList>>()
    val listFoodMatchIngreds: LiveData<List<FoodList>> = _listFoodMatchIngreds

    private val _listFoodMatchAnyIngreds = MutableLiveData<List<FoodList>>()
    val listFoodMatchAnyIngreds: LiveData<List<FoodList>> = _listFoodMatchAnyIngreds

    private val _listFoodByName = MutableLiveData<List<FoodList>>()
    val listFoodByName: LiveData<List<FoodList>> = _listFoodByName

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllFoods() {
        _isLoading.value = true
        val service = APIConfig.getApiService().getAllRecipes()
        service.enqueue(object : Callback<APIResponse> {
            override fun onResponse(
                call: Call<APIResponse>,
                response: Response<APIResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _listFood.value = responseBody.data
                } else {
                    Log.e("Error on Response", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Error on Failure", "onFailure: ${t.message}")
            }
        })
    }

    fun getMatchIngreds(ingredients: String) {
        _isLoading.value = true
        val service = APIConfig.getApiService().getMatchIngreds(ingredients)
        service.enqueue(object : Callback<APIResponse> {
            override fun onResponse(
                call: Call<APIResponse>,
                response: Response<APIResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _listFoodMatchIngreds.value = responseBody.data
                } else {
                    Log.e("Error on Response", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Error on Failure", "onFailure: ${t.message}")
            }
        })
    }

    fun getMatchAnyIngreds(ingredients: String) {
        _isLoading.value = true
        val service = APIConfig.getApiService().getMatchAnyIngreds(ingredients)
        service.enqueue(object : Callback<APIResponse> {
            override fun onResponse(
                call: Call<APIResponse>,
                response: Response<APIResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _listFoodMatchAnyIngreds.value = responseBody.data
                } else {
                    Log.e("Error on Response", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Error on Failure", "onFailure: ${t.message}")
            }
        })
    }

    fun getMatchName(name: String) {
        _isLoading.value = true
        val service = APIConfig.getApiService().getMatchName(name)
        service.enqueue(object : Callback<APIResponse> {
            override fun onResponse(
                call: Call<APIResponse>,
                response: Response<APIResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _listFoodByName.value = responseBody.data
                } else {
                    Log.e("Error on Response", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Error on Failure", "onFailure: ${t.message}")
            }
        })
    }
}
