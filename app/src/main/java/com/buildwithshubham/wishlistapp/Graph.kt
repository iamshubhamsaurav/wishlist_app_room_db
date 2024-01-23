package com.buildwithshubham.wishlistapp

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.buildwithshubham.wishlistapp.data.WishDatabase
import com.buildwithshubham.wishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }
}