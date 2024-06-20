package com.avrzll.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.avrzll.recipe.data.room.IngredientsEntity
import com.avrzll.recipe.data.room.IngredientsViewModel
import com.avrzll.recipe.data.room.IngredientsViewModelFactory

class EditIngredients : AppCompatActivity() {

    private lateinit var ingredientsViewModel: IngredientsViewModel
    private lateinit var formEditIngreds: EditText
    private lateinit var btnEdit: Button
    private lateinit var btnBack: ImageView
    private lateinit var getDataIngreds: IngredientsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bahan)

        getDataIngreds = intent.getParcelableExtra("data")!!

        val factory = IngredientsViewModelFactory.getInstance(this)
        ingredientsViewModel = ViewModelProvider(this, factory)[IngredientsViewModel::class.java]

        formEditIngreds = findViewById(R.id.form_edit_ingredients)
        btnEdit = findViewById(R.id.btn_edit_ingredients)
        btnBack = findViewById(R.id.btn_back)  // Inisialisasi btnBack

        // Set nilai awal untuk formEditIngreds
        formEditIngreds.setText(getDataIngreds.name)

        onClick()
    }

    private fun onClick() {
        btnEdit.setOnClickListener {
            if (validateInput()) {
                saveData()
            }
        }
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun validateInput(): Boolean {
        var error = 0
        if (formEditIngreds.text.toString().isEmpty()) {
            error++
            formEditIngreds.error = "Deskripsi tidak boleh kosong!"
        }
        return error == 0
    }

    private fun saveData() {
        val data =
            IngredientsEntity(
                id = getDataIngreds.id,
                name = formEditIngreds.text.toString()
            )

        if (data != null) ingredientsViewModel.updateIngredients(data)

        Toast.makeText(
            this@EditIngredients,
            "Data berhasil diubah",
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }
}
