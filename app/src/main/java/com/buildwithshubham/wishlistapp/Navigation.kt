package com.buildwithshubham.wishlistapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigation(viewModel: WishlistViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeView(navController, viewModel)
        }
        
        composable(
            Screens.AddWishlistScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {
            entry ->
            val id = if(entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            Log.d("NavigationId:", id.toString())
            AddEditDetailTextView(id = id, viewModel = viewModel, navController = navController)
        }
    }

}