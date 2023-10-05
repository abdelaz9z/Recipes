package com.etisalat.recipes.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.domain.usecase.GetRecipes
import com.etisalat.domain.usecase.RecipesCRUD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val application: Application,
    private val getRecipesUseCase: GetRecipes,
    private val recipesCRUDUseCase: RecipesCRUD
) :
    ViewModel() {

    private val _recipes: MutableStateFlow<RecipesResponse?> = MutableStateFlow(null)
    val recipes: StateFlow<RecipesResponse?> = _recipes

    private val _isNetworkAvailable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNetworkAvailable: StateFlow<Boolean>
        get() = _isNetworkAvailable

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, throwable.message.toString())
    }

    init {
        checkNetworkAvailability()
    }

    fun getRecipes() {
        viewModelScope.launch(context = Dispatchers.IO + errorHandler) {
            _recipes.value = getRecipesUseCase()
        }
    }

    private fun checkNetworkAvailability() {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isNetworkAvailable.value = true
            }

            override fun onLost(network: Network) {
                _isNetworkAvailable.value = false
            }
        }
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    val getRecipesFromLocal =
        recipesCRUDUseCase.getRecipesFromRemote().asLiveData()

    fun insertRecipesItem(recipesResponse: RecipesResponse) =
        viewModelScope.launch(context = Dispatchers.IO + errorHandler) {
            for (recipes in recipesResponse){
                recipesCRUDUseCase.insertRecipesItem(recipes)
                Log.i(TAG, "recipes: $recipes")
            }
        }

    companion object {
        private val TAG = RecipesViewModel::class.simpleName
    }
}