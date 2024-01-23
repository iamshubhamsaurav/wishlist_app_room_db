package com.buildwithshubham.wishlistapp

sealed class Screens(val route: String) {
    object HomeScreen: Screens("home_screen")
    object AddWishlistScreen: Screens("add_wishlist_screen")
}