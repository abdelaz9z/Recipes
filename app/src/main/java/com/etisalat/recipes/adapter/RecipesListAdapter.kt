package com.etisalat.recipes.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.recipes.R
import com.etisalat.recipes.databinding.RecipesListBinding
import com.etisalat.recipes.utils.AndroidUtils
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
        return UserHolder(parent.context, binding, itemClick)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindView(recipesResponseItem[position])
    }

    override fun getItemCount(): Int = recipesResponseItem.size

    class UserHolder(
        private val context: Context,
        private val binding: RecipesListBinding,
        val itemClick: (RecipesResponseItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        // here comes all the views / variables from view.findViewById()
        // Example: val textView = view.findViewById(R.id.textView1)
        @RequiresApi(Build.VERSION_CODES.O)
        fun bindView(recipesResponseItem: RecipesResponseItem) {
            // here you do all kind of assignments and logics..
            // for Example: textView.text = item.name or just name
            itemView.setOnClickListener { itemClick(recipesResponseItem) }

            // Set the data to the views
            binding.nameTextView.text = recipesResponseItem.name
            binding.headlineTextView.text = recipesResponseItem.headline
            checkDifficulty(recipesResponseItem.difficulty)
            binding.timeTextView.text = AndroidUtils().convertDuration(recipesResponseItem.time)

            // Load the image using Picasso or any other image loading library
            Picasso.get().load(recipesResponseItem.thumb).into(binding.imageView)
        }

        /**
         * Check the difficulty level and change the background tint list and text
         *
         * 0: Very easy - color (lime green)
         * 1: Easy - color (yellow)
         * 2: Moderate - color (orange)
         * 3: Difficult - color (red)
         */
        private fun checkDifficulty(difficultyLevel: Int) {
            when (difficultyLevel) {
                0 -> {
                    // Set background tint color for very easy (lime green)
                    binding.difficultyTextView.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.limeGreen)

                    // Set text for very easy (lime green)
                    binding.difficultyTextView.text = context.getString(R.string.very_easy)
                }

                1 -> {
                    // Set background tint color for easy (yellow)
                    binding.difficultyTextView.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.yellow)

                    // Set text for easy (yellow)
                    binding.difficultyTextView.text = context.getString(R.string.easy)
                }

                2 -> {
                    // Set background tint color for moderate (orange)
                    binding.difficultyTextView.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.orange)

                    // Set text for moderate (orange)
                    binding.difficultyTextView.text = context.getString(R.string.moderate)
                }

                3 -> {
                    // Set background tint color for difficult (red)
                    binding.difficultyTextView.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.red)

                    // Set text for difficult (red)
                    binding.difficultyTextView.text = context.getString(R.string.difficult)
                }
            }
        }
    }

}