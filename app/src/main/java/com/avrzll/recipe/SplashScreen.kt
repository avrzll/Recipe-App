package com.avrzll.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.avrzll.recipe.data.room.IngredientsViewModel
import com.avrzll.recipe.data.room.IngredientsViewModelFactory

class SplashScreen : AppCompatActivity() {

    private lateinit var ingredientsViewModel: IngredientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val factory = IngredientsViewModelFactory.getInstance(this)
        ingredientsViewModel = ViewModelProvider(this, factory)[IngredientsViewModel::class.java]

        Handler(Looper.getMainLooper()).postDelayed(
            {
                ingredientsViewModel.getAllIngredients().observe(this) { listIngredients ->
                    if (listIngredients.isNullOrEmpty()) {
                        goToMasukkanBahan()
                    } else {
                        goToMainActivity()
                    }
                }
            },
            1500L
        )
    }

    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun goToMasukkanBahan() {
        Intent(this, InsertIngredients::class.java).also {
            startActivity(it)
            finish()
        }
    }
}