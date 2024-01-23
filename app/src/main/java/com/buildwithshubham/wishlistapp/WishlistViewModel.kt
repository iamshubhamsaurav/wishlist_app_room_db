package com.buildwithshubham.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildwithshubham.wishlistapp.data.Wish
import com.buildwithshubham.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel() {

    var wishListTitleState by mutableStateOf("")
    var wishListDescriptionState by mutableStateOf("")

    fun onWishListTitleChanged(title: String) {
        wishListTitleState = title
    }

    fun onWishListDescriptionChanged(description: String) {
        wishListDescriptionState = description
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWish()
        }
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addAWish(wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
            getAllWishes = wishRepository.getAllWish()
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

}