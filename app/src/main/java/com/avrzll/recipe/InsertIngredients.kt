package com.avrzll.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.avrzll.recipe.data.room.IngredientsEntity
import com.avrzll.recipe.data.room.IngredientsViewModel
import com.avrzll.recipe.data.room.IngredientsViewModelFactory

class InsertIngredients : AppCompatActivity() {

    private lateinit var ingredientsViewModel: IngredientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masukkan_bahan)

        val factory = IngredientsViewModelFactory.getInstance(this)
        ingredientsViewModel = ViewModelProvider(this, factory)[IngredientsViewModel::class.java]

        val editTextUserInput: EditText = findViewById(R.id.form_edit_ingredients)
        val btnAddIngredients: Button = findViewById(R.id.btn_edit_ingredients)
        val btnBack: ImageView = findViewById(R.id.btn_back_from_masuk_bahan)

        btnBack.setOnClickListener {
            finish()
        }

        btnAddIngredients.setOnClickListener {
            val userInput = editTextUserInput.text.toString()
            val ingredientsArray = userInput.split(",").map { it.trim() }.toTypedArray()

            ingredientsArray.forEach { ingredient ->
                if (ingredient.isNotEmpty()) {
                    savedData(ingredient)
                }
            }
            Toast.makeText(
                this@InsertIngredients,
                "Success Adding Ingredients!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun savedData(name: String) {
        val data =
            IngredientsEntity(
                id = 0,
                name = name
            )
        if (data != null) ingredientsViewModel.insertIngredients(data)
        goToMainActivity()
    }


    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}

