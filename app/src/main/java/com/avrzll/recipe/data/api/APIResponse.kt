package com.avrzll.recipe.data.api

data class APIResponse(
    val error: Boolean,
    val message: String,
    val data: List<FoodList>
)
