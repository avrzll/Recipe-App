package com.avrzll.recipe

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

class SearchPage : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_page, container, false)

        return view
    }
}
