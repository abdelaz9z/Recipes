package com.etisalat.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.recipes.databinding.RecipesListBinding
import com.squareup.picasso.Picasso

class RecipesListAdapter(
    private val recipesResponseItem: RecipesResponse,
    private val itemClick: (RecipesResponseItem) -> Unit
) :
    RecyclerView.Adapter<RecipesListAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = RecipesListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindView(recipesResponseItem[position])
    }

    override fun getItemCount(): Int = recipesResponseItem.size

    class UserHolder(
        private val binding: RecipesListBinding,
        val itemClick: (RecipesResponseItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        // here comes all the views / variables from view.findViewById()
        // Example: val textView = view.findViewById(R.id.textView1)
        fun bindView(recipesResponseItem: RecipesResponseItem) {
            // here you do all kind of assignments and logics..
            // for Example: textView.text = item.name or just name
            itemView.setOnClickListener { itemClick(recipesResponseItem) }

            // Set the data to the views
            binding.nameTextView.text = recipesResponseItem.name
            binding.headlineTextView.text = recipesResponseItem.headline
//            binding.descriptionTextView.text = recipesResponseItem.description
//            binding.caloriesTextView.text = "Calories: ${recipesResponseItem.calories}"
//            binding.carbosTextView.text = "Carbos: ${recipesResponseItem.carbos}"
//            binding.fatsTextView.text = "Fats: ${recipesResponseItem.fats}"
//            binding.proteinsTextView.text = "Proteins: ${recipesResponseItem.proteins}"
//            binding.timeTextView.text = "Time: ${recipesResponseItem.time}"

            // Load the image using Picasso or any other image loading library
            Picasso.get().load(recipesResponseItem.thumb).into(binding.imageView)
        }
    }

}