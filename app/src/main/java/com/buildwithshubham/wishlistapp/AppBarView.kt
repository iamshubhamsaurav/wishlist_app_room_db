package com.buildwithshubham.wishlistapp

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(title: String, onBackNavClick: () -> Unit = {}) {

    val navigationIcon: (@Composable () -> Unit)? = {

        if(!title.contains("Wishlist")) {
            IconButton(onClick = {onBackNavClick()}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        }

    }

    TopAppBar(
        title = { Text(
            text = title,
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp),
            color = Color.White,

        )},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.app_bar_color)),
        navigationIcon = navigationIcon!!

    )
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AppBarViewNew(
//    title: String,
//    onBackNavClicked: () -> Unit = {}
//) {
//
//    TopAppBar(
//        title = {
//            Text(text = title, color = colorResource(id = R.color.white),
//                modifier = Modifier
//                    .padding(start = 4.dp)
//                    .heightIn(24.dp))
//        },
//        elevation = 3.dp,
//        backgroundColor = colorResource(id = R.color.app_bar_color)
//    )
//}