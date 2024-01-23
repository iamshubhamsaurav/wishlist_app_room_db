package com.buildwithshubham.wishlistapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buildwithshubham.wishlistapp.data.Wish
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditDetailTextView(
    id: Long,
    viewModel: WishlistViewModel,
    navController: NavController
) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if(id != 0L) {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L, "", ""))

        viewModel.wishListTitleState = wish.value.title
        viewModel.wishListDescriptionState = wish.value.description
    } else {
        viewModel.wishListTitleState = ""
        viewModel.wishListDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title =
                if (id != 0L) stringResource(id = R.string.edit_wishlist)
                else stringResource(id = R.string.add_wishlist),

            ) {//onNavBack Lambda
                navController.navigateUp()

            }
        },
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 10.dp, end = 10.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishListTextField(
                labelText = "Title",
                value = viewModel.wishListTitleState,
                onValueChanged = {
                    viewModel.onWishListTitleChanged(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishListTextField(
                labelText = "Description",
                value = viewModel.wishListDescriptionState,
                onValueChanged = {
                    viewModel.onWishListDescriptionChanged(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(viewModel.wishListTitleState.isNotEmpty() && viewModel.wishListDescriptionState.isNotEmpty()) {
                    if(id != 0L) {
                        // Update
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishListTitleState,
                                description = viewModel.wishListDescriptionState
                            )
                        )
                    } else {
                        // Add
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishListTitleState.trim(),
                                description = viewModel.wishListDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish Created"
                    }
                }
                else {
                    snackMessage.value = "Enter fields for wish to be created"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }) {
                Text(text =
                        if (id != 0L) stringResource(id = R.string.edit_wishlist)
                        else stringResource(id = R.string.add_wishlist),
                    fontSize = 18.sp
                )
                
            }
        }
    }
}


@Composable
fun WishListTextField(
    labelText: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = labelText) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(Color.Black)
    )
}