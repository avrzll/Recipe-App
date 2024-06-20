package com.avrzll.recipe.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avrzll.recipe.R
import com.avrzll.recipe.data.room.IngredientsEntity

class IngredientsRoomAdapter(private var ingredients: List<IngredientsEntity>) :
    RecyclerView.Adapter<IngredientsRoomAdapter.PostViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemMore(data: IngredientsEntity)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
        val ingredientsName: TextView = itemView.findViewById(R.id.supply_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_box_supply, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = ingredients[position]

        holder.ingredientsName.text = data.name

        holder.btnMore.setOnClickListener {
            onItemClickCallback.onItemMore(ingredients[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = ingredients.size
}
