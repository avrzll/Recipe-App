package com.avrzll.recipe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avrzll.recipe.adapter.MatchIngredsAdapter
import com.avrzll.recipe.adapter.RecommendAdapter
import com.avrzll.recipe.data.api.FoodList
import com.avrzll.recipe.data.api.FoodViewModel
import com.avrzll.recipe.data.api.FoodViewModelFactory
import com.avrzll.recipe.data.room.IngredientsViewModel
import com.avrzll.recipe.data.room.IngredientsViewModelFactory

class HomePage : Fragment() {
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var recommendAdapter: RecommendAdapter
    private lateinit var matchIngredsAdapter: MatchIngredsAdapter
    private lateinit var rvRekomendasi: RecyclerView
    private lateinit var rvFoodMatchIngreds: RecyclerView
    private lateinit var ingredientsViewModel: IngredientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = FoodViewModelFactory.getInstance()
        foodViewModel = ViewModelProvider(this, factory)[FoodViewModel::class.java]

        foodViewModel.getAllFoods()
        foodViewModel.getMatchAnyIngreds("ayam, bawang merah")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        rvRekomendasi = view.findViewById(R.id.rv_rekomendasi)
        rvFoodMatchIngreds = view.findViewById(R.id.rv_food_match_ingredients_main)
        rvRekomendasi.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvFoodMatchIngreds.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        foodViewModel.listFood.observe(viewLifecycleOwner) { foods ->
            if (foods.isNotEmpty()) {
                recommendAdapter = RecommendAdapter(foods)
                rvRekomendasi.adapter = recommendAdapter
                recommendAdapter.setOnItemClickCallback(object :
                    RecommendAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: FoodList) {
                        goToDetailFoods(data)
                    }
                })
            }
        }

        foodViewModel.listFoodMatchAnyIngreds.observe(viewLifecycleOwner) { foods ->
            if (foods.isNotEmpty()) {
                matchIngredsAdapter = MatchIngredsAdapter(foods)
                rvFoodMatchIngreds.adapter = matchIngredsAdapter
                matchIngredsAdapter.setOnItemClickCallback(object :
                    MatchIngredsAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: FoodList) {
                        goToDetailFoods(data)
                    }
                })
            }
        }
        return view
    }

    private fun goToDetailFoods(data: FoodList) {
        val navigateToDetail = Intent(requireContext(), DetailFoods::class.java)
        navigateToDetail.putExtra("data", data)
        startActivity(navigateToDetail)
    }
}
