package com.avrzll.recipe

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avrzll.recipe.R
import com.avrzll.recipe.adapter.HasilPencarianAdapter
import com.avrzll.recipe.data.api.FoodList
import com.avrzll.recipe.data.api.FoodViewModel
import com.avrzll.recipe.data.api.FoodViewModelFactory

class HasilPencarian : Fragment() {

    private lateinit var foodViewModel: FoodViewModel
    private lateinit var hasilPencarianResepAdapter: HasilPencarianAdapter
    private lateinit var rvHasilPencarian: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = FoodViewModelFactory.getInstance()
        foodViewModel = ViewModelProvider(this, factory)[FoodViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hasil_pencarian, container, false)

        rvHasilPencarian = view.findViewById(R.id.rv_hasil_pencarian_resep)
        rvHasilPencarian.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        foodViewModel.listFoodMatchIngreds.observe(viewLifecycleOwner) { foodsByIngreds ->
            if (foodsByIngreds.isNotEmpty()) {
                setupAdapter(foodsByIngreds)
            }
        }

        return view
    }

    private fun goToDetailFoods(data: FoodList) {
        val navigateToDetail = Intent(requireContext(), DetailFoods::class.java)
        navigateToDetail.putExtra("data", data)
        startActivity(navigateToDetail)
    }

    private fun setupAdapter(foodList: List<FoodList>) {
        hasilPencarianResepAdapter = HasilPencarianAdapter(foodList)
        rvHasilPencarian.adapter = hasilPencarianResepAdapter
        hasilPencarianResepAdapter.setOnItemClickCallback(object :
            HasilPencarianAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodList) {
                goToDetailFoods(data)
            }
        })
    }
}
