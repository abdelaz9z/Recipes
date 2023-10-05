package com.etisalat.recipes.ui.recipesdetals

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.recipes.R
import com.etisalat.recipes.databinding.FragmentRecipesDetailsBinding
import com.squareup.picasso.Picasso
import java.time.Duration

class RecipesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipesDetailsBinding

    private val args by navArgs<RecipesDetailsFragmentArgs>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipesDetailsBinding.inflate(inflater, container, false)

        dataCheck(args.currentRecipes)

        //
        Picasso.get().load(args.currentRecipes.thumb).into(binding.imageView)
        binding.nameTextView.text = args.currentRecipes.name
        binding.headlineTextView.text = args.currentRecipes.headline
        binding.descriptionTextView.text = args.currentRecipes.description
        binding.caloriesTextView.text = getString(R.string.calories, args.currentRecipes.calories)
        binding.carbosTextView.text = getString(R.string.carbos, args.currentRecipes.carbos)
        binding.fatsTextView.text = getString(R.string.fats, args.currentRecipes.fats)
        binding.proteinsTextView.text = getString(R.string.proteins, args.currentRecipes.proteins)
//        binding.timeTextView.text = convertDuration(args.currentRecipes.time)

        return binding.root
    }

    private fun dataCheck(recipesResponseItem: RecipesResponseItem) {
        if (recipesResponseItem.calories.isNullOrEmpty()) {
            binding.caloriesTextView.visibility = View.GONE
        }
        if (recipesResponseItem.carbos.isNullOrEmpty()) {
            binding.carbosTextView.visibility = View.GONE
        }
        if (recipesResponseItem.fats.isNullOrEmpty()) {
            binding.fatsTextView.visibility = View.GONE
        }
        if (recipesResponseItem.proteins.isNullOrEmpty()) {
            binding.proteinsTextView.visibility = View.GONE
        }
    }
}