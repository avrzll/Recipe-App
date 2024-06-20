package com.avrzll.recipe.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface IngredientsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIngredients(ingredientsEntity: IngredientsEntity)

    @Query("SELECT * FROM IngredientsEntity ORDER BY ingredients_name ASC")
    fun getAllIngredients() : LiveData<List<IngredientsEntity>>

    @Update
    fun updateIngredients(ingredientsEntity: IngredientsEntity)

    @Delete
    fun deleteIngredients(ingredientsEntity: IngredientsEntity)

}