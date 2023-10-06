package com.etisalat.recipes.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.recipes.adapter.RecipesListAdapter
import com.etisalat.recipes.databinding.FragmentRecipesBinding
import com.etisalat.recipes.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)

        // Setting up items details RecyclerView
        binding.recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recipesRecyclerView.setHasFixedSize(true)

        viewModel.isNetworkAvailable.observe(requireActivity()) { isNetworkAvailable ->
            // Perform actions based on network availability
            when (isNetworkAvailable) {
                // Network is available, perform data operations
                true -> {
                    Log.i(TAG, "Network is available")

                    binding.networkStateTextView.visibility = View.GONE

                    // get recipes
                    viewModel.getRecipes()
                    lifecycleScope.launch {
                        viewModel.recipes.collect { recipes ->
                            if (recipes != null) {
                                setAdapter(recipes)

                                viewModel.insertRecipesItem(recipes)
                            }
                        }
                    }
                }

                // Network is not available, show a message or handle the scenario
                false -> {
                    Log.e(TAG, "Network is not available")

                    binding.networkStateTextView.visibility = View.VISIBLE

                    viewModel.getRecipesFromLocal.observe(viewLifecycleOwner) { recipes ->

                        val recipesResponse = RecipesResponse()
                        recipesResponse.addAll(recipes)

                        setAdapter(recipesResponse)
                    }
                }
            }
        }

        return binding.root
    }

    /**
     * Move to recipes details fragment
     *
     * @param recipesResponseItem
     */
    private fun moveToRecipesDetailsFragment(recipesResponseItem: RecipesResponseItem) {
        val action =
            RecipesFragmentDirections.actionRecipesFragmentToRecipesDetalsFragment(
                recipesResponseItem
            )
        findNavController().navigate(action)
    }

    /**
     * set data in adapter
     */
    private fun setAdapter(recipesResponse: RecipesResponse) {
        val listAdapter = RecipesListAdapter(recipesResponse) { recipe ->
            // Here we'll receive a callback of
            // every RecyclerView item click
            // Now, perform any action here.
            // for ex: navigate to a different screen
            moveToRecipesDetailsFragment(recipe)
        }
        binding.recipesRecyclerView.adapter =
            listAdapter // Move the adapter assignment here
    }

    companion object {
        private val TAG = RecipesFragment::class.simpleName
    }
}