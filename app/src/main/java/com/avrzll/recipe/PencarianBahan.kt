package com.avrzll.recipe

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import com.avrzll.recipe.data.api.FoodViewModel
import com.avrzll.recipe.data.api.FoodViewModelFactory

class PencarianBahan : Fragment() {

    private lateinit var btnSearch: ImageButton
    private lateinit var editText: EditText
    private lateinit var foodViewModel: FoodViewModel

    interface OnFragmentInteractionListener {
        fun onReplaceFragment(fragment: Fragment)
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

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
        val view = inflater.inflate(R.layout.fragment_pencarian_bahan, container, false)

        btnSearch = view.findViewById(R.id.btn_search_bahan)
        editText = view.findViewById(R.id.pencarian_bahan)

        btnSearch.setOnClickListener {
            val userInput = editText.text.toString()
            foodViewModel.getFoodMatchIngreds(userInput)
            listener?.onReplaceFragment(HasilPencarian())
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        const val TAG = "PencarianResep"
    }
}
