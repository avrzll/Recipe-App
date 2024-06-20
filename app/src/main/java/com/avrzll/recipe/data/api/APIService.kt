package com.avrzll.recipe.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("recipes")
    fun getAllRecipes(): Call<APIResponse>

    @GET("recipes/search")
    fun getMatchIngreds(@Query("ingredients") ingredients: String): Call<APIResponse>

    @GET("recipes/search-any")
    fun getMatchAnyIngreds(@Query("ingredients") ingredients: String): Call<APIResponse>

    @GET("recipes/search")
    fun getMatchName(@Query("name") name: String): Call<APIResponse>
}
