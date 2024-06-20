package com.avrzll.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avrzll.recipe.adapter.IngredientsRoomAdapter
import com.avrzll.recipe.adapter.MatchIngredsAdapter
import com.avrzll.recipe.adapter.RecommendAdapter
import com.avrzll.recipe.data.api.FoodList
import com.avrzll.recipe.data.api.FoodViewModel
import com.avrzll.recipe.data.api.FoodViewModelFactory
import com.avrzll.recipe.data.room.IngredientsEntity
import com.avrzll.recipe.data.room.IngredientsViewModel
import com.avrzll.recipe.data.room.IngredientsViewModelFactory

class BoxPage : Fragment() {

    private lateinit var ingredientsViewModel: IngredientsViewModel
    private lateinit var ingredientsRoomAdapter: IngredientsRoomAdapter
    private lateinit var rvIngredients: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = IngredientsViewModelFactory.getInstance(requireContext())
        ingredientsViewModel = ViewModelProvider(this, factory)[IngredientsViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_box_page, container, false)

        rvIngredients = view.findViewById(R.id.rv_box_supply)
            rvIngredients.layoutManager = LinearLayoutManager(requireContext())

        ingredientsViewModel.getAllIngredients().observe(viewLifecycleOwner) { ingredsList ->
            if (ingredsList != null) {
                ingredientsRoomAdapter = IngredientsRoomAdapter(ingredsList)
                rvIngredients.adapter = ingredientsRoomAdapter

                ingredientsRoomAdapter.setOnItemClickCallback(object :
                    IngredientsRoomAdapter.OnItemClickCallback {
                    override fun onItemMore(data: IngredientsEntity) {
                        PopUpPersediaan(data).show(parentFragmentManager, PopUpPersediaan.TAG)
                    }
                })
            }
        }

        return view
    }
}
