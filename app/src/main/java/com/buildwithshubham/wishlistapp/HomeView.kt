@file:Suppress("DEPRECATION")

package com.buildwithshubham.wishlistapp

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.buildwithshubham.wishlistapp.data.DummyWish
import com.buildwithshubham.wishlistapp.data.Wish

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavController, viewModel: WishlistViewModel) {
    Scaffold(
        topBar = { AppBarView(title = "Wishlist") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                         navController.navigate(Screens.AddWishlistScreen.route + "/0L")
                },
                modifier = Modifier.padding(20.dp),
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
                items(wishList.value, key = { wish -> wish.id }) {
                    wish ->

                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if(it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                                     val color by animateColorAsState(
                                         if(dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                                         label = ""
                                     )
                            
                            val alignment = Alignment.CenterEnd
                            
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White)
                            }
                            
                        },
                        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                        dismissThresholds = { FractionalThreshold(0.25f) },
                        dismissContent = {
                            WishItem(wish = wish) {
                                val id = wish.id
                                navController.navigate(Screens.AddWishlistScreen.route + "/${id}")
                            }
                        })
                }
        }
    }
}


@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }

    }
}